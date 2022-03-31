package com.zzh.dream.study.base.configuraton;

import com.zzh.dream.study.base.bistoury.BistouryBiz;
import com.zzh.dream.study.base.interceptor.BaseInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * @description: 基础配置文件
 * @author: zhangzihao
 * @date: 26/08/2021
 **/
@Configuration
public class BaseConfiguration implements WebMvcConfigurer {

    //设置拦截器
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(baseInterceptor())
                .addPathPatterns("/base","/starter","/redis")
                .excludePathPatterns("/login");
    }

    //将拦截器注入容器
    @Bean
    public HandlerInterceptor baseInterceptor(){
        return new BaseInterceptor();
    }


    @Bean
    public BistouryBiz bistouryBiz(){
        return new BistouryBiz();
    }
}
