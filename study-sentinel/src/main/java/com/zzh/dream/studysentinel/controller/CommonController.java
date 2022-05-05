package com.zzh.dream.studysentinel.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.nacos.common.utils.UuidUtils;
import com.zzh.dream.studysentinel.ExceptionUtil;
import org.springframework.web.bind.annotation.*;

/**
 * @description: TODO 类描述
 * @author: zhangzihao
 * @date: 27/04/2022
 **/
@RestController
@RequestMapping("/sentinel")
public class CommonController {


    @GetMapping("/{id}")
    @SentinelResource(value = "sentinelGetUserById",
            fallback = "fallback",fallbackClass = ExceptionUtil.class,
            blockHandler = "handleException",blockHandlerClass = ExceptionUtil.class
    )
    public String getUserById(@PathVariable("id")String id){
        if(id.equals("4")){
            return "非法参数异常";
        }
        return "执行成功："+UuidUtils.generateUuid()+id;
    }
}
