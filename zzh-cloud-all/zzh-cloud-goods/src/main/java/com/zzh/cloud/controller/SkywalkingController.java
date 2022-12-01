package com.zzh.cloud.controller;

import com.zzh.cloud.exception.BaseException;
import com.zzh.cloud.result.CommonResult;
import com.zzh.cloud.service.ZzhGoodsService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.apache.skywalking.apm.toolkit.trace.Tag;
import org.apache.skywalking.apm.toolkit.trace.Tags;
import org.apache.skywalking.apm.toolkit.trace.Trace;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @description: skywalking控制器
 * @author: zhangzihao
 * @date: 26/08/2021
 **/
@Slf4j
@RestController
@RequestMapping("/goods")
public class SkywalkingController {
    @Autowired
    private ZzhGoodsService goodsService;


    /**
     * TraceContext.traceId() 记录链路ID
     * @Trace  标识需要链路追踪的资源
     */
    @Trace
    @Tags({@Tag(key = "param", value = "arg[0]"),
            @Tag(key = "result", value = "returnedObj")})
    @GetMapping("/skywalking/{id}")
    public CommonResult skywalkingSelectGoodsById(@PathVariable("id")String id) {
        if (StringUtils.equals(id,"123456")){
            throw new BaseException("id不能等于123456...");
        }
        return CommonResult.success(goodsService.selectById(id));
    }
}
