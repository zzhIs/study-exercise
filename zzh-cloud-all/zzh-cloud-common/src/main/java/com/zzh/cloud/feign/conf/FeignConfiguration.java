package com.zzh.cloud.feign.conf;

import com.zzh.cloud.feign.interceptor.ZzhFeignAuthRequestInterceptor;
import feign.Logger;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * @description: TODO 类描述
 * @author: zhangzihao
 * @date: 01/12/2022
 **/
//@EnableFeignClients  控制的开关由依赖的模块的启动类控制
@Configuration
public class FeignConfiguration {

    /**
     * 此处配置是全局配置
     * 有时候我们遇到 Bug，比如接口调用失败、参数没收到等问题，或者想看看调用性能，就需要配置 Feign 的日志了，以此让 Feign 把请求信息输出来。
     * 1）定义一个配置类，指定日志级别
     * NONE【性能最佳，适用于生产】：不记录任何日志（默认值）。
     * BASIC【适用于生产环境追踪问题】：仅记录请求方法、URL、响应状态代码以及执行时间。
     * HEADERS：记录BASIC级别的基础上，记录请求和响应的header。
     * FULL【比较适用于开发及测试环境定位问题】：记录请求和响应的header、body和元数据。
     */
    @Bean
    public Logger.Level feignLoggerLevel() {
        return Logger.Level.FULL;
    }

    /**
     * 自定义拦截器,实现自定义逻辑
     *
     * @return
     */
    @Bean
    public ZzhFeignAuthRequestInterceptor zzhFeignAuthRequestInterceptor(){
        return new ZzhFeignAuthRequestInterceptor();
    }

}
