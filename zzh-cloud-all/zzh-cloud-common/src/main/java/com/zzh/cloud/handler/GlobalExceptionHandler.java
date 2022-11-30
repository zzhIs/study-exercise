package com.zzh.cloud.handler;

import com.zzh.cloud.result.CommonResult;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @description: 全局异常处理器
 * @author: zhangzihao
 * @date: 30/11/2022
 **/
@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(value =Exception.class)
    @ResponseBody
    public CommonResult exceptionHandler(Exception e){
        e.printStackTrace();
        return CommonResult.failed(e.getMessage());
    }
}
