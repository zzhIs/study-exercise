package com.zzh.dream.rabbitmqcore.configuration.bind;

import com.zzh.dream.rabbitmqcore.constant.MessageQueueConstant;
import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @description: 默认队列和交换机绑定配置
 * @author: zhangzihao
 * @date: 08/03/2022
 **/
@Configuration
public class DefaultExchangeQueueConfig {


    @Bean("defaultTopicExchange")
    public TopicExchange defaultTopicExchange(){
        return new TopicExchange(MessageQueueConstant.DEFAULT_DIRECT_EXCHANGE,true,false);
    }

    @Bean("defaultQueue")
    public Queue defaultQueue(){
        return QueueBuilder.durable(MessageQueueConstant.DEFAULT_DIRECT_QUEUE).build();
    }

    @Bean("defaultBind")
    public Binding defaultBind(){
        return BindingBuilder.bind(defaultQueue()).to(defaultTopicExchange()).with(MessageQueueConstant.DEFAULT_DIRECT_KEY);
    }
}
