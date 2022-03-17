package com.zzh.dream.study.base.design.market.service;

import org.springframework.stereotype.Component;

/**
 * @description: 发送美食券
 * @author: zhangzihao
 * @date: 11/03/2022
 **/
@Component
public class FoodAward implements Award {
    @Override
    public void doaAward() {
        System.out.println("发送美食券....");
    }
}
