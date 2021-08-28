package com.zzh.dream.nacosprovider.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @description: TODO 类描述
 * @author: zhangzihao
 * @date: 28/08/2021
 **/
@RestController
@RequestMapping("/order")
public class CommonController {

    @GetMapping("user/{userId}")
    public String findOrderByUserId(@PathVariable String userId) {
        System.out.println("我是微服务提供服务...我被调用了"+userId);
        return "我是微服务【nacos-provider】的CommonController.findOrderByUserId()...我被调用了 " + userId;
    }
}
