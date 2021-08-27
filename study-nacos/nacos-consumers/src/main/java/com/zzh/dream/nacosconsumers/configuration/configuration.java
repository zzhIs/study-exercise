package com.zzh.dream.nacosconsumers.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * @description: TODO 类描述
 * @author: zhangzihao
 * @date: 27/08/2021
 **/

@Configuration
@ComponentScan("com.zzh.dream.nacosconsumers")
public class configuration {

    @Bean
    public RestTemplate restTemplate(){
        return new RestTemplate();
    }

}
