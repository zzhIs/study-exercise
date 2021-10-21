package com.zzh.dream.study.base.rabbitmq.consumer;


import com.rabbitmq.client.Channel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Slf4j
//@Component
public class FanoutQueueConsumer {

    /**
     * 发布订阅模式
     * @param message
     * @throws Exception
     */
    @RabbitListener(queues = "QUEUE_TEST_FANOUT_MODEL1")
    public void onMessage(Message message) throws Exception{
        System.out.println("发布订阅模式一：收到消息："+message.getBody().toString());
    }

    /**
     * 发布订阅模式
     * @param message
     * @throws Exception
     */
    @RabbitListener(queues = "QUEUE_TEST_FANOUT_MODEL2")
    public void onMessage2(Message message, Channel channel) throws Exception{
        System.out.println("发布订阅模式二：收到消息："+message.getBody());

        try{
            log.info("消息ID："+message.getMessageProperties().getHeader("spring_returned_message_correlation"));
            log.info("消息标签："+message.getMessageProperties().getDeliveryTag());
             /* 设置Qos机制
              * 第一个参数：单条消息的大小(0表示即无限制)
              * 第二个参数：每次处理消息的数量
              * 第三个参数：是否为consumer级别（false表示仅当前channel有效）
              */
            channel.basicQos(0,1,false);
            //todo 手动应答消息　　第一个参数是所确认消息的标识，第二参数是是否批量确认  可以再程序执行完成的时候设置签收
            channel.basicAck(message.getMessageProperties().getDeliveryTag(),false);

         }catch (Exception e){
            channel.basicReject(message.getMessageProperties().getDeliveryTag(),false);
            log.error("消息ID："+message.getMessageProperties().getHeader("spring_returned_message_correlation"));
            log.error("接收消息发送错误："+e.getMessage());
         }

    }
}
