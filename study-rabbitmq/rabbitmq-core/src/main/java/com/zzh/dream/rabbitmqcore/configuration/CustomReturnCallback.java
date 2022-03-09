package com.zzh.dream.rabbitmqcore.configuration;

import cn.hutool.json.JSON;
import cn.hutool.json.JSONUtil;
import com.zzh.dream.rabbitmqcore.dto.MessageQueueDTO;
import com.zzh.dream.rabbitmqcore.service.MessageQueueLogService;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
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
public class CustomReturnCallback implements RabbitTemplate.ReturnCallback {

//    private static final Logger log = LoggerFactory.getLogger(CustomReturnCallback.class);

    @Autowired
    private MessageQueueLogService messageQueueService;

    @Autowired
    public CustomReturnCallback(RabbitTemplate rabbitTemplate) {
        rabbitTemplate.setReturnCallback(this);
    }

    /**
     * 当消息匹配到Queue并且失败时，会通过回调returnCallback方法返回消息
     * @param message
     * @param replyCode
     * @param replyText
     * @param exchange
     * @param routingKey
     */
    @Override
    public void returnedMessage(Message message,int replyCode, String replyText, String exchange, String routingKey) {
        log.error("send message to mq fail,replyCode:[{}],replyText:[{}],exchange:[{}],routingKey:[{}]", new Object[]{replyCode, replyText, exchange, routingKey});
        MessageQueueDTO dto =JSONUtil.toBean(message.toString(), MessageQueueDTO.class);
        this.messageQueueService.failMessage(dto.getId(), replyText);
    }
}
