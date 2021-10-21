package com.zzh.dream.study.base.rabbitmq.configuration;


import org.springframework.amqp.core.*;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;

/**
 * 路由模式
 */
//@Configuration
public class RoutingModelConfiguration {


    /**
     * 申明一个路由模式的队列一
     */
    public static final String QUEUE_TEST_ROUTING_MODEL1 = "QUEUE_TEST_ROUTING_MODEL1";
    /**
     * 申明一个路由模式的队列二
     */
    public static final String QUEUE_TEST_ROUTING_MODEL2 = "QUEUE_TEST_ROUTING_MODEL2";

    /**
     * 申明一个路由模式的交换机
     */
    public static final String EXCHANGE_TEST_ROUTING_MODEL = "EXCHANGE_TEST_ROUTING_MODEL";

    /**
     * 申明路由模式模式
     * @return
     */
    @Bean("queueTestRoutingModel1")
    public Queue queueTestRoutingModel1() {
        return QueueBuilder.durable(QUEUE_TEST_ROUTING_MODEL1).build();
    }

    /**
     * 申明路由模式队列二
     * @return
     */
    @Bean("queueTestRoutingModel2")
    public Queue queueTestRoutingModel2() {
        return QueueBuilder.durable(QUEUE_TEST_ROUTING_MODEL2).build();
    }

    /**
     * 申明一个交换机  direct 1:1
     * @return
     */
    @Bean("exchangeTestRoutingModel")
    public Exchange exchangeTestRoutingModel(){
        return ExchangeBuilder.directExchange(EXCHANGE_TEST_ROUTING_MODEL).durable(true).build();
    }

    /**
     * 将队列和交换机绑定  路由模式下 routingkey不为空
     */
    @Bean
    public Binding bingQueue2Exchange3(@Qualifier("queueTestRoutingModel1") Queue queue,
                                      @Qualifier("exchangeTestRoutingModel") Exchange exchange){
        return BindingBuilder.bind(queue).to(exchange).with("order").noargs();
    }

    @Bean
    public Binding bingQueue2Exchange4(@Qualifier("queueTestRoutingModel2") Queue queue,
                                      @Qualifier("exchangeTestRoutingModel") Exchange exchange){
        return BindingBuilder.bind(queue).to(exchange).with("member").noargs();
    }


}
