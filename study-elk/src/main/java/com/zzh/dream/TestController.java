package com.zzh.dream;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

/**
 * @description: TODO 类描述
 * @author: zhangzihao
 * @date: 31/03/2022
 **/
@RestController
@RequestMapping("/elk")
public class TestController {

    @PostMapping("log")
    public String testLog(){
        System.out.println("测试记录日志");
        return "success";

    }
}
