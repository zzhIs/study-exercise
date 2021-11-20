package com.zzh.dream.nacos.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @description: TODO 类描述
 * @author: zhangzihao
 * @date: 20/11/2021
 **/
@RestController
@RequestMapping("/config")
@RefreshScope
public class ConfigController {

    @Value("${name}")
    private String name;
    @Value("${age}")
    private String age;

    @GetMapping("/get")
    public String get(){
        return name +age;
    }
}
