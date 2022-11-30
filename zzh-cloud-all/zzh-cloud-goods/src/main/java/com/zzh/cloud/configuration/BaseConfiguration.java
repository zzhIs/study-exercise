package com.zzh.cloud.configuration;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @description: 基础配置类
 * @author: zhangzihao
 * @date: 30/11/2022
 **/
@Configuration
@EnableTransactionManagement
@ComponentScan(basePackages = {"com.zzh.cloud"})
@MapperScan(value = "com.zzh.cloud.mapper")
public class BaseConfiguration {
}
