package com.zzh.cloud.controller;

import com.zzh.cloud.result.CommonResult;
import com.zzh.cloud.service.ZzhOrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @description: 订单seata控制器
 * @author: zhangzihao
 * @date: 26/08/2021
 **/
@Slf4j
@RestController
@RequestMapping("/order")
public class SeataOrderController {
    @Autowired
    private ZzhOrderService orderService;

    @PostMapping("")
    public CommonResult saveOrder(@RequestParam()String orderNum,@RequestParam("price") Integer price){
        orderService.saveOrder(orderNum,price);
        return CommonResult.success("success");
    }

}
