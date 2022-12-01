package com.zzh.cloud.sentinel;

import com.alibaba.csp.sentinel.slots.block.BlockException;

/**
 * @description: Sentinel通用异常处理逻辑
 * @author: zhangzihao
 * @date: 01/12/2022
 **/
public class SentinelGoodsExceptionUtil {

    public static String fallback(Integer id, Throwable e) {
        return "===被异常降级啦===" + id;
    }

    public static String handleException(Integer id, BlockException e) {
        return "BlockException===被限流啦===" + id;
    }
}
