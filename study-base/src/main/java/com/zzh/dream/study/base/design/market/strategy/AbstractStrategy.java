package com.zzh.dream.study.base.design.market.strategy;

import com.zzh.dream.study.base.design.market.StrategyContext;

import javax.annotation.PostConstruct;

/**
 * @description: TODO 类描述
 * @author: zhangzihao
 * @date: 11/03/2022
 **/
public abstract class AbstractStrategy implements Strategy {
    // 类注册方法  通过Spring 初始化方法执行
    @PostConstruct
    public void register() {
        StrategyContext.registerStrategy(getClass().getSimpleName(), this);
    }
}
