package com.zzh.dream.study.base.core.base.dynamic.proxy.cglib;

/**
 * @description: TODO 类描述
 * @author: zhangzihao
 * @date: 02/03/2022
 **/
public class Dog{

    final public void run(String name) {
        System.out.println("狗"+name+"----run");
    }

    public void eat() {
        System.out.println("狗----eat");
    }
}
