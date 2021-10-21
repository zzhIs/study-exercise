package com.zzh.dream.study.base.rabbitmq.consumer;


import com.rabbitmq.client.Channel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Slf4j
//@Component
public class DlxQueueConsumer {

    /**
     * 普通队列
     * @param message
     * @throws Exception
     */
    @RabbitListener(queues = "QUEUE_TEST_COMMON_MODEL")
    public void onMessage(Message message, Channel channel) throws Exception{
        System.out.println("死信-普通队列：队列收到消息："+message.getBody().toString());

        channel.basicReject(message.getMessageProperties().getDeliveryTag(),false);
        log.info("死信—普通队列收到消息---拒接消息...");
    }

    /**
     * 死信队列一
     * @param message
     * @throws Exception
     */
    @RabbitListener(queues = "QUEUE_TEST_DLX_MODEL1")
    public void onMessage2(Message message, Channel channel) throws Exception{
        System.out.println("死信队列一：收到消息："+message.getBody().toString());
        channel.basicAck(message.getMessageProperties().getDeliveryTag(),false);
    }

    /**
     * 死信队列二
     * @param message
     * @throws Exception
     */
    @RabbitListener(queues = "QUEUE_TEST_DLX_MODEL2")
    public void onMessage3(Message message, Channel channel) throws Exception{
        System.out.println("死信队列二：收到消息："+message.getBody().toString());
        channel.basicAck(message.getMessageProperties().getDeliveryTag(),false);
    }


}
