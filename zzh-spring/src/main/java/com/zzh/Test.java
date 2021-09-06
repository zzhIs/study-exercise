package com.zzh;

import com.spring.ZzhApplicationContext;
import com.zzh.service.OrderService;

/**
 * @description: TODO 类描述
 * @author: zhangzihao
 * @date: 04/09/2021
 **/
public class Test {
    public static void main(String[] args) {
        ZzhApplicationContext applicationContext = new ZzhApplicationContext(AppConfig.class);

        OrderService orderService = (OrderService)applicationContext.getBean("orderService");

        orderService.test();
    }
}
