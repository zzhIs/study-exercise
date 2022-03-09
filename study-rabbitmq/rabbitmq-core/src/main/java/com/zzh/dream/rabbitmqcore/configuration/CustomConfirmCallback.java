package com.zzh.dream.rabbitmqcore.configuration;

import com.zzh.dream.rabbitmqcore.service.MessageQueueLogService;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @description: TODO 类描述
 * @author: zhangzihao
 * @date: 08/03/2022
 **/
@Slf4j
@Component
public class CustomConfirmCallback implements RabbitTemplate.ConfirmCallback {
//    private static final Logger log = LoggerFactory.getLogger(CustomConfirmCallback.class);

    @Autowired
    private MessageQueueLogService messageQueueService;


    @Override
    public void confirm(CorrelationData correlationData, boolean ack, String cause) {
        if (ack) {
            log.debug("收到MQ的ack,id:[{}]", correlationData.getId());
            this.messageQueueService.confirmPushToMq(correlationData.getId());
            log.info("消息[{}]成功投递", correlationData.getId());
        } else {
            log.error("消息[{}]投递到MQ失败,cause:{}", correlationData.getId(), cause);
            this.messageQueueService.failMessage(correlationData.getId(), cause);
        }
    }

    /**
     * 绑定RabbitTemplate和回调
     *
     * @param rabbitTemplate
     */
    @Autowired
    public CustomConfirmCallback(RabbitTemplate rabbitTemplate) {
        rabbitTemplate.setConfirmCallback(this);
    }
}
