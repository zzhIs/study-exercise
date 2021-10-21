package com.zzh.dream.study.base.core.base.dynamic.proxy;

/**
 * @description: 动态委托类
 * @author: zhangzihao
 * @date: 17/08/2021
 **/
public class Vendor implements Shell{
    @Override
    public void shell() {
        System.out.println("shell方法执行的实现...");
    }

    @Override
    public void add() {
        System.out.println("add方法执行接口的实现");
    }
}
