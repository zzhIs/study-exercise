package com.zzh.dream.rabbitmqconsumer.consumer;

import cn.hutool.json.JSONUtil;
import com.zzh.dream.rabbitmqcore.abs.AbstractMqConsumer;
import com.zzh.dream.rabbitmqcore.constant.MessageQueueConstant;
import com.zzh.dream.rabbitmqcore.dto.MessageQueueDTO;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @description: 延时消息==监听的队列是死信队列
 * @author: zhangzihao
 * @date: 08/03/2022
 **/
@Component
@RabbitListener(queues = MessageQueueConstant.POLL_PAY_STATUS_DEAD_QUEUE)
public class DelayDefaultConsumer extends AbstractMqConsumer {

    @Override
    protected Object process(MessageQueueDTO dto) {
        //todo 执行消费者逻辑
        System.out.println("延时消费完成"+ JSONUtil.toJsonStr(dto.getMessage()));
        return "message";
    }
}
