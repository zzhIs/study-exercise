package com.zzh.dream.studysentinel.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @description: 定义一个feign管理的接口
 * @author: zhangzihao
 * @date: 27/04/2022
 **/
@FeignClient(value = "nacos-provider",path = "/provider/order",fallback = FallbackTestFeignService.class)
public interface TestFeignService {

    @RequestMapping("/getById/{userId}")
    public String findOrderByUserId(@PathVariable("userId") Integer userId);

}
