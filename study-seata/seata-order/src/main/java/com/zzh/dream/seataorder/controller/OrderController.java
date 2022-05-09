package com.zzh.dream.seataorder.controller;

import com.zzh.dream.seataorder.service.OrderService;
import io.seata.core.context.RootContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @description: TODO 类描述
 * @author: zhangzihao
 * @date: 07/05/2022
 **/
@RestController
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @PostMapping("")
    public String saveOrder(@RequestParam("goodsIds") String goodsIds) throws Exception {
        orderService.saveOrder(goodsIds);
        return "下单成功" + goodsIds;
    }


}
