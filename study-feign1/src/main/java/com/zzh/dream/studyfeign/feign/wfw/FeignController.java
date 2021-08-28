package com.zzh.dream.studyfeign.feign.wfw;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * @description: TODO 类描述
 * @author: zhangzihao
 * @date: 27/08/2021
 **/
@RestController
@RequestMapping("/feign")
public class FeignController {
    @Autowired
    private OrderFeignService orderFeignService;

    @GetMapping("/order/user/{userId}")
    public String echoAppName(@PathVariable("userId")String userId){
        return orderFeignService.findOrderByUserId(userId);
    }
}
