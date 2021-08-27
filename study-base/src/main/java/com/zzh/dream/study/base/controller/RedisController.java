package com.zzh.dream.study.base.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @description: 基础控制器
 * @author: zhangzihao
 * @date: 26/08/2021
 **/
@RestController
@RequestMapping("/redis")
public class RedisController {

    @Autowired
    private RedisTemplate<String,String> redisTemplate;

    @GetMapping("/key/{key}/value/{value}")
    public String setRedisKeyValue(@PathVariable("key")String key,@PathVariable("value")String value){
        Boolean isAdd = redisTemplate.opsForValue().setIfPresent(key, value);
        if (!isAdd) {
            redisTemplate.opsForValue().set(key,value);
            return "success";
        }
        return "已经存在了...";
    }

    @GetMapping("/key/{key}")
    public String getRedisKeyValue(@PathVariable("key")String key){
        return redisTemplate.opsForValue().get(key);
    }
}
