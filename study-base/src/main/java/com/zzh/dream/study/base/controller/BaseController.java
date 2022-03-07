package com.zzh.dream.study.base.controller;

import com.zzh.dream.study.base.biz.UserInsertBiz;
import com.zzh.dream.study.base.entity.User;
import com.zzh.dream.study.base.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @description: 基础控制器
 * @author: zhangzihao
 * @date: 26/08/2021
 **/
@RestController
@RequestMapping("/base")
public class BaseController {

    @Autowired
    private UserService userService;
    @Autowired
    private UserInsertBiz userInsertBiz;

    @GetMapping("/user/{id}")
    public User getUserById(@PathVariable("id")Integer id){
        return userService.selectById(id);
    }

    @PostMapping("/user/insert")
    public String insert(){
        userInsertBiz.insert();
        return "success";
    }
}
