package com.zzh.dream.seatainventory.service;

/**
 * @description: TODO 类描述
 * @author: zhangzihao
 * @date: 09/05/2022
 **/
public interface SeataInventoryService {

    /**
     * 根据商品id扣减库存
     *
     * @param goodsId
     */
    void countDownInventory(String goodsId) throws Exception;
}
