package com.zzh.dream.studyfeign.ribbon.configuration;

//import com.netflix.loadbalancer.IRule;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * @description: Spring官方提供了两种负载均衡的客户端：
 * RestTemplate
 * RestTemplate是Spring提供的用于访问Rest服务的客户端，RestTemplate提供了多种便捷访问远程Http服务的方法，
 * 能够大大提高客户端的编写效率。默认情况下，RestTemplate默认依赖jdk的HTTP连接工具。
 *
 * 使用方案只需要结合RestTemplate使用在注入时使用    @LoadBalanced注解即可
 *
 * @author: zhangzihao
 * @date: 27/08/2021
 **/

@Configuration
public class RibbonConfiguration {

    @Bean
    @LoadBalanced
    public RestTemplate restTemplate(){
        return new RestTemplate();
    }

    /*
    负载均衡器
    全局配置：调用其他微服务，一律使用指定的负载均衡算法
        RandomRule： 随机选择一个Server。
        RetryRule： 对选定的负载均衡策略机上重试机制，在一个配置时间段内当选择Server不成功，则一直尝试使用subRule的方式选择一个可用的server。
        RoundRobinRule： 轮询选择， 轮询index，选择index对应位置的Server。
        AvailabilityFilteringRule： 过滤掉一直连接失败的被标记为circuit tripped的后端Server，并过滤掉那些高并发的后端Server或者使用一个AvailabilityPredicate来包含过滤server的逻辑，其实就是检查status里记录的各个Server的运行状态。
        BestAvailableRule： 选择一个最小的并发请求的Server，逐个考察Server，如果Server被tripped了，则跳过。
        WeightedResponseTimeRule： 根据响应时间加权，响应时间越长，权重越小，被选中的可能性越低。
        ZoneAvoidanceRule： 默认的负载均衡策略，即复合判断Server所在区域的性能和Server的可用性选择Server，在没有区域的环境下，类似于轮询(RandomRule)
        NacosRule:  同集群优先调用
     */
//    @Bean
//    public IRule iRule(){
//        // 指定使用Nacos提供的负载均衡策略（优先调用同一集群的实例，基于随机权重）
//        return new NacosRule();
//    }

    /*
    ribbon全局配置自定义的负载均衡器策略
     */
//    public IRule iRule(){
//        return new NacosRandomWithWeightRule();
//    }

}
