package com.zzh.dream.study.base.rabbitmq.configuration;


import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.QueueBuilder;
import org.springframework.context.annotation.Bean;

/**
 * 简单模式配置文件 队列和监听一对一
 * 和工作队列模式   队列和监听一对多
 * 注意：一个消息只能被一个监听消费
 */
//@Configuration
public class SimpleModelConfiguration {

    /**
     * 申明一个队列名称
     */
    public static final String QUEUE_TEST_SIMPLE_MODEL = "QUEUE_TEST_SIMPLE_MODEL";

    /**
     * 申明队列
     * @return
     */
    @Bean("queueTestSimpleModel")
    public Queue queueTestSimpleModel() {
        return QueueBuilder.durable(QUEUE_TEST_SIMPLE_MODEL).build();
    }




    /**
     * 使用简单队列测试可靠消息发送
     */
    public static final String QUEUE_TEST_SIMPLE_MODEL1 = "QUEUE_TEST_SIMPLE_MODEL1";
    @Bean("queueTestSimpleModel1")
    public Queue queueTestSimpleModel1() {
        return QueueBuilder.durable(QUEUE_TEST_SIMPLE_MODEL1).build();
    }

}
