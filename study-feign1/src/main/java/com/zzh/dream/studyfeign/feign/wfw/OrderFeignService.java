package com.zzh.dream.studyfeign.feign.wfw;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @description: 微服务远程服务的接口
 * @author: zhangzihao
 * @date: 28/08/2021
 *   configuration = FeignConfiguration.class  局部配置日志级别，FeignConfiguration不能使用@Configuration注解 否则就是全局配置
 **/
@FeignClient(value = "nacos-provider",path = "/provider/order",configuration = FeignConfiguration.class)
public interface OrderFeignService {

    @RequestMapping("/user/{userId}")
    public String findOrderByUserId(@PathVariable("userId") String userId);
}
