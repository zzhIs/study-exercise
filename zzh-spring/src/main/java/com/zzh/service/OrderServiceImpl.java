package com.zzh.service;

import com.spring.Autowired;
import com.spring.Component;

@Component("orderService")
public class OrderServiceImpl implements OrderService {

    @Autowired
    private UserService userService;

    public void test() {
        System.out.println("OrderServiceImpl.test方法执行了...");
        userService.test();
    }
}
