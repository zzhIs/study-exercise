package com.zzh.dream.studyfeign.feign.only;

import feign.Headers;
import feign.RequestLine;

/**
 * @description: 远程服务的接口
 * @author: zhangzihao
 * @date: 28/08/2021
 **/
public interface RemoteService {

    @Headers({"Content-Type: application/json","Accept: application/json"})
    @RequestLine("GET /order/findOrderByUserId/{userId}")
    public Object findOrderByUserId(String userId);
}
