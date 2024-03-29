package com.zzh.cloud.feign.interf.gateway;

import com.zzh.cloud.feign.conf.FeignConfiguration;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @description: 测试回调gateway接口
 * @author: zhangzihao
 * @date: 01/12/2022
 **/
@FeignClient(value = "zzh-cloud-gateway",path = "/gateway",configuration = FeignConfiguration.class)
public interface GatewayServiceFeign {

    @GetMapping("/user/{id}")
    String select(@PathVariable("id") String id);
}
