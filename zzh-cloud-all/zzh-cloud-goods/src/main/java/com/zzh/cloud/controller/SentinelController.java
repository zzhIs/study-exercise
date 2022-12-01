package com.zzh.cloud.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.zzh.cloud.exception.BaseException;
import com.zzh.cloud.result.CommonResult;
import com.zzh.cloud.sentinel.SentinelCommonExceptionUtil;
import com.zzh.cloud.sentinel.SentinelGoodsExceptionUtil;
import com.zzh.cloud.service.ZzhGoodsService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @description: sentinel控制器
 * @author: zhangzihao
 * @date: 26/08/2021
 **/
@Slf4j
@RestController
@RequestMapping("/goods")
public class SentinelController {
    @Autowired
    private ZzhGoodsService goodsService;


    @GetMapping("/sentinel/{id}")
    @SentinelResource(value = "sentinelSelectGoodsById",fallback = "fallback",blockHandler = "handleException",
            blockHandlerClass = SentinelGoodsExceptionUtil.class)
    public CommonResult sentinelSelectGoodsById(@PathVariable("id")String id) {
        if (StringUtils.equals(id,"123456")){
            throw new BaseException("id不能等于123456...");
        }
        return CommonResult.success(goodsService.selectById(id));
    }
}
