package com.zzh.service;

import com.spring.ApplicationContext;
import com.spring.ApplicationContextAware;
import com.spring.Component;
import com.spring.InitializingBean;

@Component("userService")
public class UserService implements InitializingBean,ApplicationContextAware {

    private ApplicationContext applicationContext;

    @Override
    public void afterPropertiesSet() {
        System.out.println("UserService.afterPropertiesSet方法执行了...");
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) {
        System.out.println("UserService.setApplicationContext方法执行了,获取到了容器");
        this.applicationContext = applicationContext;
    }

    public void test(){
        System.out.println("UserService.test()方法执行...");
    }
}
