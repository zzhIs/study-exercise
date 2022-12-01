package com.zzh.cloud.feign.interf.order;

import com.zzh.cloud.feign.conf.FeignConfiguration;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @description:
 * @author: zhangzihao
 * @date: 01/12/2022
 **/
@FeignClient(value = "zzh-cloud-order",path = "/zzh-order",configuration = FeignConfiguration.class)
public interface ZzhOrderServiceFeign {

    @RequestMapping("/order/{id}")
    Object findOrderById(@PathVariable("id") String id);
}
