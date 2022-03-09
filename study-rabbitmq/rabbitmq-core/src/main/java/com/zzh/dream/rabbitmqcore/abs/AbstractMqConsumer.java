package com.zzh.dream.rabbitmqcore.abs;

import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONUtil;
import com.rabbitmq.client.Channel;
import com.zzh.dream.rabbitmqcore.dto.MessageQueueDTO;
import com.zzh.dream.rabbitmqcore.entity.TbMessageQueueLog;
import com.zzh.dream.rabbitmqcore.enums.MessageQueueStatus;
import com.zzh.dream.rabbitmqcore.helper.RmqHelper;
import com.zzh.dream.rabbitmqcore.service.MessageQueueLogService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanNameAware;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;

import java.io.IOException;

/**
 * @author: zhangzihao
 * @date: 07/03/2022
 **/
@Slf4j
public abstract class AbstractMqConsumer implements BeanNameAware, ApplicationContextAware {

    @Autowired
    protected RmqHelper rmqHelper;
    @Autowired
    protected MessageQueueLogService messageQueueService;
    private int retryConsumeNum = 3;
    private String beanName;
    private ApplicationContext applicationContext;

    @Override
    public void setBeanName(String beanName) {
        this.beanName = beanName;
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    /**
     * @param messageStr
     * @param channel
     * @param message
     * @RabbitListener 和 @RabbitHandler 搭配使用
     * @RabbitListener 可以标注在类上面，需配合 @RabbitHandler 注解一起使用
     * @RabbitListener 标注在类上面表示当有收到消息的时候，就交给 @RabbitHandler 的方法处理，具体使用哪个方法处理，根据 MessageConverter 转换后的参数类型
     */
    @RabbitHandler
    public void handler(String messageStr, Channel channel, Message message) {

        MessageQueueDTO dto = null;

        try {
            dto =JSONUtil.toBean(messageStr, MessageQueueDTO.class);
            dto.setConsumeNum(ObjectUtils.isEmpty(dto.getConsumeNum()) ? 0L : dto.getConsumeNum() + 1L);

            //todo 1.检测消息是否存在
            TbMessageQueueLog mqLog = this.messageQueueService.getExists(dto.getId());
            if (!ObjectUtils.isEmpty(mqLog)) {

                //todo 2.如果已经完成，直接设置消息已经消费
                if (StrUtil.equals(MessageQueueStatus.FINISHED.getCode(), mqLog.getStatus())) {
                    log.info("消息已被消费，不可重复消费，messageId:{}", dto.getId());
                    channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
                    return;
                }

                if (ObjectUtils.isEmpty(mqLog.getConfirmTime()) && StrUtil.equals("0", mqLog.getIsPushed())) {
                    Thread.sleep(1000L);
                }

                //todo 3.消费消息
                this.finishConsumeWithTransaction(dto);

                //todo 4.回复ack
                channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
                log.info("消息[{}]消费完成", dto.getId());
                return;
            }

            log.info("消息不存在，message:{}", messageStr);
            channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
        } catch (Exception e ) {

            //todo 5.处理异常
            this.dealWithException(messageStr, dto,e.getMessage() , message.getMessageProperties().getDeliveryTag(), channel);
            return;
        } finally {

        }
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public Object finishConsumeWithTransaction(MessageQueueDTO dto) {

        //todo 1.修改状态为消费中 --通过数据库乐观锁保证幂等性
        if (!this.messageQueueService.tryConsume(dto.getId())) {
            log.error("尝试消费失败，messageId:{}", dto.getId());
            throw new NullPointerException("尝试消费失败");
        } else {

            //todo 2.设置为消费时间
            this.messageQueueService.firstConfirm(dto.getId());
            // todo 调用监听者程序完成消费
            Object ob = this.process(dto);
            //todo 设置为消费完成
            this.messageQueueService.finishMessage(dto.getId());
            return ob;
        }
    }

    private void dealWithException(String message, MessageQueueDTO dto, String err, long tag, Channel channel) {
        log.error("处理消息失败，message:{}\n{}", message, err);

        try {
            if (!ObjectUtils.isEmpty(dto)) {
                TbMessageQueueLog messageQueueLog = null;

                try {
                    // todo 1.消费失败 设置失败状态和次数
                    messageQueueLog = this.messageQueueService.failMessage(dto.getId(), err);
                } catch (Exception e) {
                    log.error("获取消息日志失败，detail:" + e.getMessage());
                }

                //todo 2.如果消费次数小于设置的失败次数阈值，则再次投递到MQ下次再消费
                if ((ObjectUtils.isEmpty(messageQueueLog) || messageQueueLog.getConsumeNum() < (long) this.retryConsumeNum) && dto.getConsumeNum() < (long) this.retryConsumeNum) {
                    this.reTryMessage(channel, tag, dto);
                } else {
                    channel.basicAck(tag, false);
                }
            } else {
                channel.basicAck(tag, false);
            }
        } catch (Exception var10) {
            var10.printStackTrace();
        }

    }

    protected void reTryMessage(Channel channel, long tag, MessageQueueDTO dto){
        log.info("消息重新投递");
        try {
            channel.basicAck(tag, false);
            this.rmqHelper.sendMessageToMq(dto);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 留给子类使用传参
     *
     * @param dto
     * @return
     */
    protected abstract Object process(MessageQueueDTO dto);

}


