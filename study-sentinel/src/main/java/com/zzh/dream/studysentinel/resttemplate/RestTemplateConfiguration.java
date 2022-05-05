package com.zzh.dream.studysentinel.resttemplate;

import com.alibaba.cloud.sentinel.annotation.SentinelRestTemplate;
import com.alibaba.csp.sentinel.annotation.aspectj.SentinelResourceAspect;
import com.zzh.dream.studysentinel.ExceptionUtil;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * @description: TODO 类描述
 * @author: zhanihao
 * @date: 27/04/2022
 **/

@Configuration
public class RestTemplateConfiguration {

    /**
     * Spring Cloud Alibaba Sentinel 支持对 RestTemplate 的服务调用使用 Sentinel 进行保护，在构造 RestTemplate bean的时候需要加上 @SentinelRestTemplate 注解。
     *
     * @SentinelRestTemplate 注解的属性支持限流(blockHandler, blockHandlerClass)和降级(fallback, fallbackClass)的处理。
     *
     * Sentinel RestTemplate 限流的资源规则提供两种粒度：
     *
     * httpmethod:schema://host:port/path：协议、主机、端口和路径
     * httpmethod:schema://host:port：协议、主机和端口
     * @return
     */
    @Bean
    @LoadBalanced
    @SentinelRestTemplate(fallback = "fallback",fallbackClass = ExceptionUtil.class,
            blockHandler = "handleException",blockHandlerClass = ExceptionUtil.class)
    public RestTemplate restTemplate(){
        return new RestTemplate();
    }
}
