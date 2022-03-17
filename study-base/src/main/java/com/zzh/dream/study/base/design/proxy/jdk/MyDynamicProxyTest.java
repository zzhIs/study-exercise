package com.zzh.dream.study.base.design.proxy.jdk;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @description: TODO 类描述
 * @author: zhangzihao
 * @date: 17/08/2021
 **/
public class MyDynamicProxyTest {

    public static void main(String[] args) {

        /**
         * 1.
         */
        MyDynamicProxy myDynamicProxy = new MyDynamicProxy(new ShellImpl());
        //获取代理类实例sell
        Shell proxyInstance = (Shell)Proxy.newProxyInstance(Shell.class.getClassLoader(), new Class[]{Shell.class}, myDynamicProxy);
        proxyInstance.shell();
        proxyInstance.add();


        /**
         * 2
         */
        //获取代理类实例sell
        Shell shell = (Shell)Proxy.newProxyInstance(Shell.class.getClassLoader(), new Class[]{Shell.class}, new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                System.out.println("befor...");
                Object invoke = method.invoke(new ShellImpl(), args);
                System.out.println("after...");
                return invoke;
            }
        });
    }
}
