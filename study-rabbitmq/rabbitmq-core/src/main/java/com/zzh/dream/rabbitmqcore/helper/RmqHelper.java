package com.zzh.dream.rabbitmqcore.helper;

import cn.hutool.json.JSONUtil;
import com.zzh.dream.rabbitmqcore.dto.MessageQueueDTO;
import com.zzh.dream.rabbitmqcore.entity.TbMessageQueueLog;
import com.zzh.dream.rabbitmqcore.service.MessageQueueLogService;
import com.zzh.dream.rabbitmqcore.util.UUIDUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.support.TransactionSynchronizationAdapter;
import org.springframework.transaction.support.TransactionSynchronizationManager;
import org.springframework.util.StringUtils;

/**
 * @description: 消息发送帮助类
 * @author: zhangzihao
 * @date: 15/01/2022
 **/
@Component
public class RmqHelper {

    private static final Logger log = LoggerFactory.getLogger(RmqHelper.class);
    @Autowired
    private RabbitTemplate rabbitTemplate;
    @Autowired
    private MessageQueueLogService messageQueueLogService;

    public RmqHelper() {
    }

    /**
     * 消息发送端使用此工具类发送消息
     *
     * @param dto
     */
    public void push(MessageQueueDTO dto) {
        //todo 消息先落库
        messageQueueLogService.insertMessageLog(dto);

        //发送到MQ
        this.sendMessageToMq(dto);
    }

    public void sendMessageToMq(MessageQueueDTO messageQueueLog) {
        if (TransactionSynchronizationManager.isSynchronizationActive()) {
            log.info("事务同步器已开启，延迟执行");
            TransactionSynchronizationManager.registerSynchronization(new RmqHelper.RmqSynchronization(messageQueueLog));
        } else {
            log.info("事务同步器未开启，直接执行任务");
            (new RmqHelper.RmqSynchronization(messageQueueLog)).run();
        }
    }

    public class RmqSynchronization extends TransactionSynchronizationAdapter {
        private MessageQueueDTO message;

        public RmqSynchronization(MessageQueueDTO dto) {
            this.message = dto;
        }

        public void afterCommit() {
            this.run();
        }

        /**
         * 推送消息
         */
        private void run() {
            RmqHelper.log.info("开始推送消息到MQ");
            String id = this.message.getId();
            CorrelationData correlationData = new CorrelationData(StringUtils.isEmpty(id) ? UUIDUtils.get32UUID() : id);
            RmqHelper.this.rabbitTemplate.convertAndSend(this.message.getExchangeName(), this.message.getRoutingKey(),
                    JSONUtil.toJsonStr(this.message), correlationData);
        }
    }
}
