package com.zzh.dream.study.base.core.base.dynamic.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * @description: 我的动态代理类
 * @author: zhangzihao
 * @date: 17/08/2021
 **/
public class MyDynamicProxy implements InvocationHandler {

    private Object obi;

    public MyDynamicProxy() {
    }

    public MyDynamicProxy(Object obi) {
        this.obi = obi;
    }

    @Override
    public Object invoke(Object obj, Method method, Object[] args) throws Throwable {
        System.out.println("代理前执行before...");
        Object result = method.invoke(obi, args);
        System.out.println("代理后执行after...");
        return result;
    }
}
