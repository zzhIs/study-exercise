package com.zzh.dream.studysentinel;

import com.alibaba.csp.sentinel.slots.block.BlockException;

/**
 * @description: TODO 类描述
 * @author: zhangzihao
 * @date: 27/04/2022
 **/
public class ExceptionUtil {
    public static String fallback(Integer id,Throwable e){
        return "===被异常降级啦===";
    }

    public static String handleException(Integer id, BlockException e){
        return "===被限流啦===";
    }
}
