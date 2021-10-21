package com.zzh.dream.study.base.rabbitmq.consumer;


import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

//@Component
public class WorkQueueConsumer {

    /**
     * 工作队列一 同简单模式一样，只不过是创建多个消费者来监听队列，MQ会轮询往多个监听器里面推送消息
     * @param message
     * @throws Exception
     */
    @RabbitListener(queues = "QUEUE_TEST_SIMPLE_MODEL")
    public void onMessage(Message message) throws Exception{
        System.out.println("工作队列一：队列收到消息："+message.getBody().toString());
    }

    /**
     * 工作队列一
     * @param message
     * @throws Exception
     */
    @RabbitListener(queues = "QUEUE_TEST_SIMPLE_MODEL")
    public void onMessage2(Message message) throws Exception{
        System.out.println("工作队列二：收到消息："+message.getBody().toString());
    }


}
