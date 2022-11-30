package com.zzh.cloud;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @description: 基础控制器
 * @author: zhangzihao
 * @date: 26/08/2021
 **/
@RestController
@RequestMapping("/gateway")
public class BaseController {

    @PostMapping("/user")
    public String insert(){
        System.out.println("查询成功...");
        return "success";
    }
}
