package com.zzh.dream.rabbitmqcore.annotation;

import com.zzh.dream.rabbitmqcore.configuration.RabbitMqConfiguration;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * @description: 开启消息队列注解
 * @author: zhangzihao
 * @date: 15/01/2022
 **/
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Import({RabbitMqConfiguration.class})
public @interface EnableZzhMq {
}
