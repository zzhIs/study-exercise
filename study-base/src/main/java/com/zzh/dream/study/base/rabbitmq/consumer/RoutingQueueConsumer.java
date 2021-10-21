package com.zzh.dream.study.base.rabbitmq.consumer;


import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

//@Component
public class RoutingQueueConsumer {

    /**
     * 路由模式
     * @param message
     * @throws Exception
     */
    @RabbitListener(queues = "QUEUE_TEST_ROUTING_MODEL1")
    public void onMessage(Message message) throws Exception{
        System.out.println("路由模式模式一：收到消息："+message.getBody().toString());
    }

    /**
     * 路由模式
     * @param message
     * @throws Exception
     */
    @RabbitListener(queues = "QUEUE_TEST_ROUTING_MODEL2")
    public void onMessage2(Message message ) throws Exception{
        System.out.println("路由模式模式二：收到消息："+message.getBody());
    }


}
