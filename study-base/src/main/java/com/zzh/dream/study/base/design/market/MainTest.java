package com.zzh.dream.study.base.design.market;

/**
 * @description:
 * 要做一个营销，需要用户参与一个活动，然后完成一系列的任务，最后可以得到一些奖励作为回报。
 * 活动的奖励包含美团外卖、酒旅和美食等多种品类券，现在需要你帮忙设计一套奖励发放方案。
 * @author: zhangzihao
 * @date: 11/03/2022
 **/
public class MainTest {

    public static void main(String[] args) {
        StrategyContext strategyContext = new StrategyContext();

        strategyContext.getStrategy("").issue("");
    }
}
