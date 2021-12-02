package com.zzh.dream.studyfeign.ribbon.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * @description: TODO 类描述
 * @author: zhangzihao
 * @date: 27/08/2021
 **/
@RestController
@RequestMapping("/ribbon")
public class RibbonController {
    @Autowired
    private RestTemplate restTemplate;
    @Value("${spring.application.name}")
    private String appName;

    @GetMapping("/app-name")
    public String echoAppName(){
        System.out.println("我是微服务消费者，基于ribbon调用服务啦...");
        String url = String.format("http://nacos-provider/provider/order/user/1",appName);
        System.out.println("request url:"+url);
        return restTemplate.getForObject(url,String.class);
    }
}
