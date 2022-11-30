package com.zzh.cloud.service;

import com.zzh.cloud.entity.ZzhGoods;
import com.zzh.cloud.exception.BaseException;

/**
 * @description: TODO 类描述
 * @author: zhangzihao
 * @date: 30/11/2022
 **/
public interface ZzhGoodsService {

    ZzhGoods selectById(String id);

    /**
     * 扣减库存
     *
     * @param id
     * @param num
     */
    void countDownInventory(String id, Integer num) throws Exception;
}
