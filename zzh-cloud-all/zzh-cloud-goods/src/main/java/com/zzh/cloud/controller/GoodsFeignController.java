package com.zzh.cloud.controller;

import com.zzh.cloud.feign.gateway.ZzhGatewayServiceFeign;
import com.zzh.cloud.result.CommonResult;
import com.zzh.cloud.service.ZzhGoodsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @description: 基础控制器
 * @author: zhangzihao
 * @date: 26/08/2021
 **/
@Slf4j
@RestController
@RequestMapping("/goods")
public class GoodsFeignController {
    @Autowired
    private ZzhGatewayServiceFeign gatewayServiceFeign;


    /**
     * 通过feign调用gateway接口
     *
     * @param id
     * @return
     */
    @GetMapping("/feign_gateway/{id}")
    public CommonResult selectById(@PathVariable("id") String id) {

        return CommonResult.success(gatewayServiceFeign.select(id));
    }

}
