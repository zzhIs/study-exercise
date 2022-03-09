package com.zzh.dream.rabbitmqcore.configuration.bind;

import com.zzh.dream.rabbitmqcore.constant.MessageQueueConstant;
import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @description: 死信队列配置文件，下单
 * @author: zhangzihao
 * @date: 08/03/2022
 **/
@Configuration
public class TtlExchangeQueueConfig {


    // ===================================================================================================
    @Bean
    public TopicExchange orderTopicExchange() {
        return new TopicExchange(MessageQueueConstant.POLL_PAY_STATUS_EXCHANGE, true, false);
    }

    @Bean
    public Queue orderQueue() {
        return QueueBuilder.durable(MessageQueueConstant.POLL_PAY_STATUS_QUEUE)
                //绑定死信交换机
                .withArgument("x-dead-letter-exchange", MessageQueueConstant.POLL_PAY_STATUS_DEAD_EXCHANGE)
                //死信路由key
                .withArgument("x-dead-letter-routing-key", MessageQueueConstant.POLL_PAY_STATUS_DEAD_ROUTE_KEY)
                //多久进入死信队列 五分钟
                .withArgument("x-message-ttl", 30 * 1000L)
                .build();
    }

    @Bean
    public Binding orderBind() {
        return BindingBuilder.bind(orderQueue()).to(orderTopicExchange()).with(MessageQueueConstant.POLL_PAY_STATUS_ROUTE_KEY);
    }


    //==============================================死信队列================================================================
    @Bean
    public TopicExchange orderDeadExchange() {
        return new TopicExchange(MessageQueueConstant.POLL_PAY_STATUS_DEAD_EXCHANGE, true, false);
    }

    @Bean
    public Queue orderDeadQueue() {

        return QueueBuilder.durable(MessageQueueConstant.POLL_PAY_STATUS_DEAD_QUEUE).build();
    }

    @Bean
    public Binding orderDeadKey() {
        return BindingBuilder.bind(orderDeadQueue()).to(orderDeadExchange()).with(MessageQueueConstant.POLL_PAY_STATUS_DEAD_ROUTE_KEY);
    }

}
