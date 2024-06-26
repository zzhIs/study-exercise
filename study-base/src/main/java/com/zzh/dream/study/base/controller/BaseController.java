package com.zzh.dream.study.base.controller;

import com.zzh.dream.study.base.biz.UserInsertBiz;
import com.zzh.dream.study.base.entity.User;
import com.zzh.dream.study.base.service.UserService;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

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

    @GetMapping("/user_detail/{id}")
    public User getUserDetailById(@PathVariable("id")Integer id){
        return userService.getUserDetailById(id);
    }

    @GetMapping("/user_one/{id}")
    public User selectOne(@PathVariable("id")Integer id){
        return userService.selectOne(id);
    }

    @PostMapping("/user/insert")
    public String insert(){
        userInsertBiz.insert();
        return "success";
    }

    @PostMapping("/date_time")
    public String dateTime(@RequestBody DateTimeDTO dateTimeDTO){
        System.out.println(dateTimeDTO.getName());
        System.out.println(dateTimeDTO.getLocalDateTime());


        return "success";
    }

    @Data
    static class DateTimeDTO{
        private String name;
        private LocalDateTime localDateTime;
    }
}
