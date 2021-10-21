package com.zzh.dream.study.base.rabbitmq.consumer;


import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

//@Component
public class SimpleModelConsumer {

    /**
     * 简单队列的监听者
     * @param message
     * @throws Exception
     */
    @RabbitListener(queues = "QUEUE_TEST_SIMPLE_MODEL")
    public void onMessage(Message message) throws Exception{
        System.out.println("简单队列收到消息："+message.getBody().toString());
    }


}
