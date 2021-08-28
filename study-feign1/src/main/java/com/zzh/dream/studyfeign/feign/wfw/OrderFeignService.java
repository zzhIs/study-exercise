package com.zzh.dream.studyfeign.feign.wfw;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @description: 微服务远程服务的接口
 * @author: zhangzihao
 * @date: 28/08/2021
 **/
@FeignClient(value = "nacos-provider",path = "/provider/order")
public interface OrderFeignService {

    @RequestMapping("/user/{userId}")
    public String findOrderByUserId(@PathVariable("userId") String userId);
}
