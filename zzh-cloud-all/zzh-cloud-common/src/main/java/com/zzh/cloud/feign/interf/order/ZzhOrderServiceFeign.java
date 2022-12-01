package com.zzh.cloud.feign.interf.order;

import com.zzh.cloud.feign.conf.FeignConfiguration;
import com.zzh.cloud.result.CommonResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @description:
 * @author: zhangzihao
 * @date: 01/12/2022
 **/
@FeignClient(value = "zzh-cloud-order",path = "/zzh-order",configuration = FeignConfiguration.class)
public interface ZzhOrderServiceFeign {

    @RequestMapping("/order/{id}")
    CommonResult findOrderById(@PathVariable("id") String id);

    @PostMapping("/order")
    CommonResult saveOrder(@RequestParam("orderNum") String orderNum, @RequestParam("price") Integer price);
}
