package com.zzh.dream.study.base.design.market;

import com.zzh.dream.study.base.design.market.strategy.Strategy;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * @description: TODO 类描述
 * @author: zhangzihao
 * @date: 11/03/2022
 **/
@Component
public class StrategyContext {
    private static final Map<String, Strategy> registerMap = new HashMap<>();
    // 注册策略
    public static void registerStrategy(String rewardType, Strategy strategy) {
        registerMap.putIfAbsent(rewardType, strategy);
    }
    // 获取策略
    public static Strategy getStrategy(String rewardType) {
        return registerMap.get(rewardType);
    }
}
