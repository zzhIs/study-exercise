package com.zzh.dream.studyfeign.feign.wfw;
import feign.Logger;
import feign.Request;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


/**
 * @description:
 *
 * @author: zhangzihao
 * @date: 27/08/2021
 **/

@Configuration
public class FeignConfiguration {


    /**
    此处配置是全局配置
    有时候我们遇到 Bug，比如接口调用失败、参数没收到等问题，或者想看看调用性能，就需要配置 Feign 的日志了，以此让 Feign 把请求信息输出来。
    1）定义一个配置类，指定日志级别
    NONE【性能最佳，适用于生产】：不记录任何日志（默认值）。
    BASIC【适用于生产环境追踪问题】：仅记录请求方法、URL、响应状态代码以及执行时间。
    HEADERS：记录BASIC级别的基础上，记录请求和响应的header。
    FULL【比较适用于开发及测试环境定位问题】：记录请求和响应的header、body和元数据。
     */
    @Bean
    public Logger.Level feignLoggerLevel(){
        return Logger.Level.FULL;
    }


    /**
     * 自定义拦截器
     * @return
     */
    @Bean
    public FeignAuthRequestInterceptor feignAuthRequestInterceptor(){
        return new FeignAuthRequestInterceptor();
    }


    /**
     * 配置全局超时时间
     *
     * feign:
     *   client:
     *     config:
     *       mall-order:  #对应微服务
     *         # 连接超时时间，默认2s
     *         connectTimeout: 5000
     *         # 请求处理超时时间，默认5s
     *         readTimeout: 10000
     *
     * @return
     */
    @Bean
    public Request.Options options() {
        return new Request.Options(5000, 10000);
    }


    /**
     * 客户端组件配置
     * Feign 中默认使用 JDK 原生的 URLConnection 发送 HTTP 请求，我们可以集成别的组件来替换掉 URLConnection，比如 Apache HttpClient，OkHttp。
     * Feign发起调用真正执行逻辑：feign.Client#execute   （扩展点）
     *
     * 例如：OkHttp
     * 添加依赖
     * <dependency>
     *           <groupId>io.github.openfeign</groupId>
     *           <artifactId>feign-okhttp</artifactId>
     * </dependency>
     *
     * 修改配置
     * feign:
     *   #feign 使用 okhttp
     *   httpclient:
     *     enabled: false
     *   okhttp:
     *     enabled: true
     */


}
