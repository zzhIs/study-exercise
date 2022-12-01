package com.zzh.cloud.feign.interceptor;

import feign.Request;
import feign.RequestInterceptor;
import feign.RequestTemplate;

import java.util.Collection;

/**
 * @description: 通过拦截器实现认证
 * @author: zhangzihao
 * @date: 28/08/2021
 **/
public class ZzhFeignAuthRequestInterceptor implements RequestInterceptor {
    @Override
    public void apply(RequestTemplate requestTemplate){
        System.out.println("我是自定义拦截器FeignAuthRequestInterceptor，我执行...");
        //requestTemplate 可以得到请求参数
        //设值
        requestTemplate.header("header","vvvvv");
//        requestTemplate.body("133");
        Collection<String> requestVariables = requestTemplate.getRequestVariables();
        System.out.println(requestVariables);

        Request request = requestTemplate.request();
        System.out.println(request);
    }
}
