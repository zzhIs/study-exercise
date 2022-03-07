package com.zzh.dream.study.base.core.base.dynamic.proxy.jdk;

import java.lang.reflect.Proxy;

/**
 * @description: TODO 类描述
 * @author: zhangzihao
 * @date: 17/08/2021
 **/
public class MyDynamicProxyTest {

    public static void main(String[] args) {

        MyDynamicProxy myDynamicProxy = new MyDynamicProxy(new Vendor());

        //获取代理类实例sell
        Shell proxyInstance = (Shell)Proxy.newProxyInstance(Shell.class.getClassLoader(), new Class[]{Shell.class}, myDynamicProxy);

        proxyInstance.shell();

        proxyInstance.add();


    }
}
