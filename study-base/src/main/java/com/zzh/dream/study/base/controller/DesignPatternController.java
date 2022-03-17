package com.zzh.dream.study.base.controller;

import com.zzh.dream.study.base.biz.UserInsertBiz;
import com.zzh.dream.study.base.design.market.StrategyContext;
import com.zzh.dream.study.base.design.market.TestBiz;
import com.zzh.dream.study.base.entity.User;
import com.zzh.dream.study.base.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @description: 设计模式测试控制器
 * @author: zhangzihao
 * @date: 26/08/2021
 **/
@RestController
@RequestMapping("/design")
public class DesignPatternController {

    @Autowired
    private TestBiz testBiz;



    @PostMapping("/strategy")
    public String insert(){
        testBiz.test();
        return "success";
    }
}
