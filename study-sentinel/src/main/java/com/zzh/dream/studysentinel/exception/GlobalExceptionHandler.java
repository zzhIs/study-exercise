package com.zzh.dream.studysentinel.exception;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * @description: TODO 类描述
 * @author: zhangzihao
 * @date: 27/04/2022
 **/
@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler({Exception.class})
    public String handler(Exception e){
        return "全局异常处理器返回:"+e.getMessage();
    }
}
