package com.zzh.dream.rabbitmqcore.configuration;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.amqp.core.AcknowledgeMode;
import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.listener.RabbitListenerContainerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

/**
 * @description: 配置类
 * @author: zhangzihao
 * @date: 15/01/2022
 **/
@Configuration
@ComponentScan({"com.zzh.dream"})
@MapperScan({"com.zzh.dream.rabbitmq.mapper"})
@PropertySource("classpath:application.properties")
public class RabbitMqConfiguration {

    @Value("${spring.rabbitmq.host}")
    private String host;
    @Value("${spring.rabbitmq.port}")
    private Integer port;
    @Value("${spring.rabbitmq.username}")
    private String username;
    @Value("${spring.rabbitmq.password}")
    private String password;
    @Value("${spring.rabbitmq.virtualHost}")
    private String virtualHost;
    @Value("${spring.rabbitmq.prefetch_count:10}")
    private Integer prefetchCount;
    @Value("${spring.rabbitmq.consumer_num:1}")
    private Integer consumerNum;
    @Value("${spring.rabbitmq.max_consumer_num:10}")
    private Integer maxConsumerNum;

    public RabbitMqConfiguration() {
    }


    /**
     * 设置连接工厂
     * @return
     */
    @Bean
    public ConnectionFactory connectionFactory(){
        CachingConnectionFactory connectionFactory = new CachingConnectionFactory();
        connectionFactory.setHost(this.host);
        connectionFactory.setPort(this.port);
        connectionFactory.setVirtualHost(this.virtualHost);
        connectionFactory.setUsername(this.username);
        connectionFactory.setPassword(this.password);
        connectionFactory.setPublisherConfirms(true);
        connectionFactory.setPublisherReturns(true);
        return connectionFactory;
    }


    @Bean
    public RabbitListenerContainerFactory rabbitListenerContainerFactory(ConnectionFactory connectionFactory){
        SimpleRabbitListenerContainerFactory containerFactory = new SimpleRabbitListenerContainerFactory();
        containerFactory.setConnectionFactory(connectionFactory);
        containerFactory.setAcknowledgeMode(AcknowledgeMode.MANUAL);
        containerFactory.setPrefetchCount(this.prefetchCount);
        containerFactory.setConcurrentConsumers(this.consumerNum);
        containerFactory.setMaxConcurrentConsumers(this.maxConsumerNum);
        return containerFactory;
    }

    @Bean
    public RabbitTemplate rabbitTemplate() {
        RabbitTemplate rabbitTemplate = new RabbitTemplate(this.connectionFactory());
        return rabbitTemplate;
    }

}
