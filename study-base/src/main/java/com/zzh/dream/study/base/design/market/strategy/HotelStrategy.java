package com.zzh.dream.study.base.design.market.strategy;

import com.zzh.dream.study.base.design.market.service.HotelAward;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @description: TODO 类描述
 * @author: zhangzihao
 * @date: 11/03/2022
 **/
@Component
public class HotelStrategy extends AbstractStrategy{

    /**
     * 适配器模式--发放具体奖励
     */
    @Autowired
    private HotelAward hotelAward;

    @Override
    public void issue(Object... params) {
        System.out.println("参加住酒店活动...");
        hotelAward.doaAward();
    }
}
