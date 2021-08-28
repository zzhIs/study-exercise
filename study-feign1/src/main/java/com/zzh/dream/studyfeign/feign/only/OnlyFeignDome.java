package com.zzh.dream.studyfeign.feign.only;

import feign.Feign;
import feign.Request;
import feign.Retryer;
//import feign.jackson.JacksonDecoder;
//import feign.jackson.JacksonEncoder;

/**
 * @description: 单独使用feign的案例
 * @author: zhangzihao
 * @date: 28/08/2021
 **/
public class OnlyFeignDome {
    public static void main(String[] args) {
        //基于json
        // encoder指定对象编码方式，decoder指定对象解码方式
        RemoteService service = Feign.builder()
//                .encoder(new JacksonEncoder())  //
//                .decoder(new JacksonDecoder())
                .options(new Request.Options(1000, 3500))
                .retryer(new Retryer.Default(5000, 5000, 3))
                .target(RemoteService.class, "http://localhost:8020/");
        System.out.println(service.findOrderByUserId("1"));
    }
}
