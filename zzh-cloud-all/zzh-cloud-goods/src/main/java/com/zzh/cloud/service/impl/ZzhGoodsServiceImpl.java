package com.zzh.cloud.service.impl;

import cn.hutool.core.util.ObjectUtil;
import com.zzh.cloud.entity.ZzhGoods;
import com.zzh.cloud.exception.BaseException;
import com.zzh.cloud.mapper.ZzhGoodsMapper;
import com.zzh.cloud.service.ZzhGoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @description: TODO 类描述
 * @author: zhangzihao
 * @date: 30/11/2022
 **/
@Service
public class ZzhGoodsServiceImpl implements ZzhGoodsService {
    @Autowired
    private ZzhGoodsMapper goodsMapper;

    @Override
    public ZzhGoods selectById(String id) {
        return goodsMapper.selectByPrimaryKey(id);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void countDownInventory(String id, Integer num) throws Exception {
        ZzhGoods zzhGoods = this.selectById(id);
        if (ObjectUtil.isNull(zzhGoods)) {
            throw new BaseException("商品不存在...");
        }
        if (zzhGoods.getCount() < num) {
            throw new BaseException("库存不足...");
        }
        goodsMapper.updateByPrimaryKey(zzhGoods.setCount(zzhGoods.getCount() - num));

        if (num > 10) {
            throw new BaseException("主动失败测试事务是否正常...");
        }
    }
}
