package com.zzh.dream.study.base.design.market.strategy;

import com.zzh.dream.study.base.design.market.service.WaimaiAward;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @description: TODO 类描述
 * @author: zhangzihao
 * @date: 11/03/2022
 **/
@Component
public class WaimaiStrategy extends AbstractStrategy{
    /**
     * 适配器模式--发放具体奖励
     */
    @Autowired
    private WaimaiAward waimaiAward;

    @Override
    public void issue(Object... params) {
        System.out.println("参加外卖活动...");
        waimaiAward.doaAward();
    }
}
