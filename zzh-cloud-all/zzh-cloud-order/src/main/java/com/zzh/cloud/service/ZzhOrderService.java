package com.zzh.cloud.service;

/**
 * @description: TODO 类描述
 * @author: zhangzihao
 * @date: 01/12/2022
 **/
public interface ZzhOrderService {

    /**
     * 新增订单
     *
     * @param orderNum
     * @param price
     */
    void saveOrder(String orderNum, Integer price);
}
