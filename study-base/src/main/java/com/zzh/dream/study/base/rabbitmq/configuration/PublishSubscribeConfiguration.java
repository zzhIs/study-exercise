package com.zzh.dream.study.base.rabbitmq.configuration;


import org.springframework.amqp.core.*;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;

/**
 * 发布订阅模式
 */
//@Configuration
public class PublishSubscribeConfiguration {
    
    /**
     * 申明一个发布订阅的队列一
     */
    public static final String QUEUE_TEST_FANOUT_MODEL1 = "QUEUE_TEST_FANOUT_MODEL1";
    /**
     * 申明一个发布订阅的队列二
     */
    public static final String QUEUE_TEST_FANOUT_MODEL2 = "QUEUE_TEST_FANOUT_MODEL2";

    /**
     * 申明一个发布订阅的交换机
     */
    public static final String EXCHANGE_TEST_FANOUT_MODEL = "EXCHANGE_TEST_FANOUT_MODEL";

    /**
     * 申明发布订阅队列
     * @return
     */
    @Bean("queueTestFanoutModel1")
    public Queue queueTestFanoutModel1() {
        return QueueBuilder.durable(QUEUE_TEST_FANOUT_MODEL1).build();
    }

    /**
     * 申明发布订阅队列二
     * @return
     */
    @Bean("queueTestFanoutModel2")
    public Queue queueTestFanoutModel2() {
        return QueueBuilder.durable(QUEUE_TEST_FANOUT_MODEL2).build();
    }

    /**
     * 申明一个交换机
     * @return
     */
    @Bean("exchangeTestFanoutModel")
    public Exchange exchangeTestFanoutModel(){
        return ExchangeBuilder.fanoutExchange(EXCHANGE_TEST_FANOUT_MODEL).durable(true).build();
    }

    /**
     * 将队列和交换机绑定  订阅模式下 routingkey为空
     */
    @Bean
    public Binding bingQueue2Exchange(@Qualifier("queueTestFanoutModel1") Queue queue,
                                      @Qualifier("exchangeTestFanoutModel") Exchange exchange){
        return BindingBuilder.bind(queue).to(exchange).with("").noargs();
    }

    @Bean
    public Binding bingQueue2Exchange2(@Qualifier("queueTestFanoutModel2") Queue queue,
                                      @Qualifier("exchangeTestFanoutModel") Exchange exchange){
        return BindingBuilder.bind(queue).to(exchange).with("").noargs();
    }


}
