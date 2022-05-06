package com.zzh.dream.studygateway.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @description: TODO 类描述
 * @author: zhangzihao
 * @date: 05/05/2022
 **/
@RestController
@RequestMapping("/gateway")
public class TestController {

    @GetMapping("/test")
    public String testGateway(){
        return "testGateway执行成功....";
    }
}
