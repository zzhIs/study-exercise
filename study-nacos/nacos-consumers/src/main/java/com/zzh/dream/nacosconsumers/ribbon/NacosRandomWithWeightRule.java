//package com.zzh.dream.nacosconsumers.ribbon;
//
//import com.alibaba.cloud.nacos.NacosDiscoveryProperties;
//import com.alibaba.cloud.nacos.ribbon.NacosServer;
//import com.alibaba.nacos.api.exception.NacosException;
//import com.alibaba.nacos.api.naming.NamingService;
//import com.alibaba.nacos.api.naming.pojo.Instance;
//import com.netflix.client.config.IClientConfig;
//import com.netflix.loadbalancer.AbstractLoadBalancerRule;
//import com.netflix.loadbalancer.DynamicServerListLoadBalancer;
//import com.netflix.loadbalancer.Server;
//import org.springframework.beans.factory.annotation.Autowired;
//
///**
// * @description: 自定义负载均衡策略
// * 通过实现 IRule 接口可以自定义负载策略，主要的选择服务逻辑在 choose 方法中。
// * 1）实现基于Nacos权重的负载均衡策略
// * @author: zhangzihao
// * @date: 28/08/2021
// **/
//
//public class NacosRandomWithWeightRule extends AbstractLoadBalancerRule {
//    @Autowired
//    private NacosDiscoveryProperties nacosDiscoveryProperties;
//
//    @Override
//    public Server choose(Object o) {
//        DynamicServerListLoadBalancer loadBalancer = (DynamicServerListLoadBalancer)getLoadBalancer();
//        String serverName = loadBalancer.getName();
//        NamingService namingService = nacosDiscoveryProperties.namingServiceInstance();
//        try {
//            Instance instance = namingService.selectOneHealthyInstance(serverName);
//            return new NacosServer(instance);
//        } catch (NacosException e) {
//            e.printStackTrace();
//        }
//        return null;
//    }
//
//    @Override
//    public void initWithNiwsConfig(IClientConfig iClientConfig) {
//
//    }
//}
