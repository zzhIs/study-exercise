package com.zzh.dream.seataorder.controller;

import com.zzh.dream.seataorder.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.apache.skywalking.apm.toolkit.trace.Tag;
import org.apache.skywalking.apm.toolkit.trace.Tags;
import org.apache.skywalking.apm.toolkit.trace.Trace;
import org.apache.skywalking.apm.toolkit.trace.TraceContext;
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
@Slf4j
@RestController
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private OrderService orderService;


    /**
     * TraceContext.traceId() 记录链路ID
     * @Trace  标识需要链路追踪的资源
     */
    @Trace
    @Tags({@Tag(key = "param", value = "arg[0]"),
            @Tag(key = "result", value = "returnedObj")})
    @PostMapping("")
    public String saveOrder(@RequestParam("goodsIds") String goodsIds) throws Exception {
        String traceId = TraceContext.traceId();
        log.info("订单服务—跟踪的tranceID是{}",traceId);
        orderService.saveOrder(goodsIds);
        return "下单成功" + goodsIds;
    }


}
