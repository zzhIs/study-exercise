package com.zzh.dream.study.base.design.market.strategy;

/**
 * @description:
 * 首先设计出了策略接口，并通过适配器的思想将各个下游接口类适配成策略类
 * @author: zhangzihao
 * @date: 11/03/2022
 **/
// 策略接口
public interface Strategy {
    void issue(Object ... params);
}
