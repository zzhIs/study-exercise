package com.zzh.dream.study.base.rabbitmq.configuration;


import org.springframework.amqp.core.*;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;

/**
 * 死信模式：
 * DLX 也是一个正常的 Exchange，和一般的 Exchange 没有区别，它能在任何的队列上被指定，实际上就是设置某个队列的属性。
 * 当这个队列中有死信时，RabbitMQ 就会自动的将这个消息重新发布到设置的 Exchange上去，进而被路由到另一个队列。
 * 可以监听这个队列中的消息做相应的处理
 * <p>
 * 正常工作队列上绑定两个交换机（正常+死信）
 */
//@Configuration
public class DlxModelConfiguration {

    /**
     * 申明普通队列，普通交换机，绑定到主题交换机
     */
    public static final String QUEUE_TEST_COMMON_MODEL = "QUEUE_TEST_COMMON_MODEL";
    public static final String EXCHANGE_TEST_COMMON_MODEL = "EXCHANGE_TEST_COMMON_MODEL";

    /**
     * 申明死信队列，死信交换机，绑定到普通队列上
     */
    public static final String QUEUE_TEST_DLX_MODEL1 = "QUEUE_TEST_DLX_MODEL1";
    public static final String QUEUE_TEST_DLX_MODEL2 = "QUEUE_TEST_DLX_MODEL2";
    public static final String EXCHANGE_TEST_DLX_MODEL = "EXCHANGE_TEST_DLX_MODEL";

    /**
     * 死信队列一
     *
     * @return
     */
    @Bean("queueTestDlxModel1")
    public Queue queueTestDlxModel1() {
        return QueueBuilder.durable(QUEUE_TEST_DLX_MODEL1).build();
    }

    /**
     * 死信队列二
     *
     * @return
     */
    @Bean("queueTestDlxModel2")
    public Queue queueTestDlxModel2() {
        return QueueBuilder.durable(QUEUE_TEST_DLX_MODEL2).build();
    }

    /**
     * 死信交换机
     *
     * @return
     */
    @Bean("exchangeTestDlxModel")
    public Exchange exchangeTestDlxModel() {
        return ExchangeBuilder.topicExchange(EXCHANGE_TEST_DLX_MODEL).durable(true).build();
    }

    /**
     * 将死信队列和死信交换机绑定 routingkey == routing.a.#
     */
    @Bean
    public Binding bingQueue2ExchangeDlx(@Qualifier("queueTestDlxModel1") Queue queue,
                                         @Qualifier("exchangeTestDlxModel") Exchange exchange) {
        return BindingBuilder.bind(queue).to(exchange).with("routing.a.#").noargs();
    }
    @Bean
    public Binding bingQueue2ExchangeDlx2(@Qualifier("queueTestDlxModel2") Queue queue,
                                          @Qualifier("exchangeTestDlxModel") Exchange exchange) {
        return BindingBuilder.bind(queue).to(exchange).with("routing.b.#").noargs();
    }

    /**
     * 工作队列---绑定死信交换机和死信路由key
     *
     * @return
     */
//    @Bean("queueTestCommonModel")
    @Bean
    public Queue queueTestCommonModel() {
        return QueueBuilder
                .durable(QUEUE_TEST_COMMON_MODEL)  //绑定普通队列名称
                .deadLetterExchange(EXCHANGE_TEST_DLX_MODEL)  //绑定死信交换机
                .deadLetterRoutingKey("routing.a.key") //设置死信路由
                .ttl(3000)                  //设置多长时间进入死信队列
                //.withArgument("x-delivery-limit", )         //设置参数 可以穿map 一次赋值全部
                .build();
    }

    @Bean("exchangeTestCommonModel")
    public Exchange exchangeTestCommonModel() {
        return ExchangeBuilder.topicExchange(EXCHANGE_TEST_COMMON_MODEL).durable(true).build();
    }
    @Bean
    public Binding bingQueue2ExchangeCommon(@Qualifier("queueTestCommonModel") Queue queue,
                                            @Qualifier("exchangeTestCommonModel") Exchange exchange) {
        return BindingBuilder.bind(queue).to(exchange).with("#").noargs();
    }

}
