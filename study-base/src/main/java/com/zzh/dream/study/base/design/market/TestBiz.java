package com.zzh.dream.study.base.design.market;

import com.zzh.dream.study.base.design.market.strategy.FoodStrategy;
import com.zzh.dream.study.base.design.market.strategy.HotelStrategy;
import com.zzh.dream.study.base.design.market.strategy.WaimaiStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @description: 要做一个营销，需要用户参与一个活动，然后完成一系列的任务，最后可以得到一些奖励作为回报。
 * 活动的奖励包含美团外卖、酒旅和美食等多种品类券，现在需要你帮忙设计一套奖励发放方案。
 * @author: zhangzihao
 * @date: 11/03/2022
 **/
@Component
public class TestBiz {

    @Autowired
    private StrategyContext strategyContext;

    public void test() {
        strategyContext.getStrategy(new WaimaiStrategy().getClass().getSimpleName()).issue("12312");
        strategyContext.getStrategy(new HotelStrategy().getClass().getSimpleName()).issue("12312");
        strategyContext.getStrategy(new FoodStrategy().getClass().getSimpleName()).issue("12312");
    }
}
