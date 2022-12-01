package com.zzh.cloud.configuration;

import com.alibaba.cloud.sentinel.annotation.SentinelRestTemplate;
import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.zzh.cloud.sentinel.SentinelCommonExceptionUtil;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * @description: 公共配置类
 * @author: zhangzihao
 * @date: 30/11/2022
 **/
@Configuration
public class CommonConfiguration {


    /**
     * 整合feign没有此配置会报异常
     * 【Java异常】com.netflix.client.ClientException: Load balancer does not have available server for client
     *  使用ribbon作为负载均衡   #nacos-discovery中默认引入了ribbon
     * @return
     */
    /**
     * RestTemplate整合sentinel
     */
    @Bean
    @LoadBalanced
    @SentinelRestTemplate(fallback = "fallback",fallbackClass = SentinelCommonExceptionUtil.class,
            blockHandler = "handleException",blockHandlerClass = SentinelCommonExceptionUtil.class)
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }


}
