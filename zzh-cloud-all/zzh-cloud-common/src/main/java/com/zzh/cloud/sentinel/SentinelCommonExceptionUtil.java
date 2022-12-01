package com.zzh.cloud.sentinel;

import com.alibaba.cloud.sentinel.rest.SentinelClientHttpResponse;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;

/**
 * @description: Sentinel通用异常处理逻辑
 * @author: zhangzihao
 * @date: 01/12/2022
 **/
public class SentinelCommonExceptionUtil {

    public static SentinelClientHttpResponse fallback(HttpRequest request, byte[] body,
                                                      ClientHttpRequestExecution requestExecution,
                                                      BlockException exception) {
        return new SentinelClientHttpResponse("===被异常降级啦===" );
    }

    public static SentinelClientHttpResponse handleException(HttpRequest request, byte[] body,
                                         ClientHttpRequestExecution requestExecution,
                                         BlockException exception) {
        return new SentinelClientHttpResponse("BlockException===被限流啦===");
    }
}
