package com.zzh.dream.study.base.design.proxy.jdk;

/**
 * @description: 被代理类
 * @author: zhangzihao
 * @date: 17/08/2021
 **/
public class ShellImpl implements Shell{
    @Override
    public void shell() {
        System.out.println("shell方法执行的实现...");
    }

    @Override
    public void add() {
        System.out.println("add方法执行接口的实现");
    }
}
