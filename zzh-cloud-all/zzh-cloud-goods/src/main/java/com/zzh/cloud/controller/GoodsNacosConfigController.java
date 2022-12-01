package com.zzh.cloud.controller;

import com.zzh.cloud.configuration.UserConfigInfo;
import com.zzh.cloud.result.CommonResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @description: 基础控制器
 * @author: zhangzihao
 * @date: 26/08/2021
 **/
@Slf4j
@RestController
@RequestMapping("/goods")
public class GoodsNacosConfigController {


    @Autowired
    private UserConfigInfo user;

    @GetMapping("/user")
    public CommonResult selectUserInfo(){
        return CommonResult.success(String.format("姓名：%s,年龄：%s",user.getName(),user.getAge()));
    }
}
