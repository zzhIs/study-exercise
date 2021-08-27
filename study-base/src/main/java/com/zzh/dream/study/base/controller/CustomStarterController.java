package com.zzh.dream.study.base.controller;


import com.zzh.starter.MyStarterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @description: 自定义启动器使用
 * @author: zhangzihao
 * @date: 26/08/2021
 **/
@RestController
@RequestMapping("/starter")
public class CustomStarterController {

    @Autowired
    private MyStarterService myStarterService;

    @GetMapping("")
    public String getUserById(){
        return myStarterService.starter();
    }
}
