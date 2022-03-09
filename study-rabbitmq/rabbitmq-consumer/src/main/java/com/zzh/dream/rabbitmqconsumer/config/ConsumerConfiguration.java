package com.zzh.dream.rabbitmqconsumer.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

/**
 * @description: TODO 类描述
 * @author: zhangzihao
 * @date: 08/03/2022
 **/

//此处的路径扫描会覆盖启动类上的
@MapperScan(value = "com.zzh.dream.rabbitmqconsumer.mapper")
@Configuration
public class ConsumerConfiguration {
}
