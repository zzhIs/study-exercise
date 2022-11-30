package com.zzh.cloud.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @description: 基础控制器
 * @author: zhangzihao
 * @date: 26/08/2021
 **/
@RestController
@RequestMapping("/user")
public class GoodsController {

    @GetMapping("")
    public String insert(){
        System.out.println("查询成功...");
        return "success";
    }
}
