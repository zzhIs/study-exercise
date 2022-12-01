package com.zzh.cloud;

import org.springframework.web.bind.annotation.*;

/**
 * @description: 基础控制器
 * @author: zhangzihao
 * @date: 26/08/2021
 **/
@RestController
@RequestMapping("/gateway")
public class BaseController {

    @GetMapping("/user/{id}")
    public String select(@PathVariable("id")String id){
        System.out.println("查询成功...");
        return "success"+id;
    }
}
