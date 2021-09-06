package com.zzh.service;

import com.spring.BeanPostProcessor;
import com.spring.Component;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

@Component("zzhBeanPostProcessor")
public class ZzhBeanPostProcessor implements BeanPostProcessor {

    @Override
    public Object postProcessAfterInitialization(String beanName, Object bean) {
        if ("orderService".equals(beanName)) {
            System.out.println("执行后置处理器ZzhBeanPostProcessor.after方法...");

            Object proxy = Proxy.newProxyInstance(ZzhBeanPostProcessor.class.getClassLoader(), bean.getClass().getInterfaces(), new InvocationHandler() {
                @Override
                public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                    System.out.println("切面逻辑");
                    return method.invoke(bean, args);
                }
            });

            return proxy;

        }

        return bean;
    }

    @Override
    public Object postProcessBeforeInitialization(String beanName, Object bean){
        System.out.println("执行后置处理器ZzhBeanPostProcessor.before方法......");
        return bean;
    }


}
