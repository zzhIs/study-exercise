package com.zzh.dream.seatainventory.service.impl;

import com.zzh.dream.seatainventory.entity.SeataInventory;
import com.zzh.dream.seatainventory.mapper.SeataInventoryMapper;
import com.zzh.dream.seatainventory.service.SeataInventoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * @description: TODO 类描述
 * @author: zhangzihao
 * @date: 09/05/2022
 **/
@Service
public class SeataInventoryServiceImpl implements SeataInventoryService {

    @Autowired
    private SeataInventoryMapper seataInventoryMapper;
    @Override
    public void countDownInventory(String goodsId) throws Exception {
        SeataInventory seataInventory = selectByGoodsId(goodsId);
        if (seataInventory != null && seataInventory.getCount() >=1) {
            seataInventory.setCount(seataInventory.getCount()-1).setUptName("zzh").setUptTime(new Date());
            int update = seataInventoryMapper.updateByPrimaryKey(seataInventory);
            if (update == 1) {
                return;
            }
        }
        throw  new Exception("库存不够,扣减库存失败...");
    }


    private SeataInventory selectByGoodsId(String goodId){
        return seataInventoryMapper.selectByGoodsId(goodId);
    }


}
