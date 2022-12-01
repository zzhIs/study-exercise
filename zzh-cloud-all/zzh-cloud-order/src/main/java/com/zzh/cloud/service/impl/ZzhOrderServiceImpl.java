package com.zzh.cloud.service.impl;

import cn.hutool.core.lang.UUID;
import com.zzh.cloud.entity.ZzhOrder;
import com.zzh.cloud.exception.BaseException;
import com.zzh.cloud.mapper.ZzhOrderMapper;
import com.zzh.cloud.service.ZzhOrderService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * @description: TODO 类描述
 * @author: zhangzihao
 * @date: 01/12/2022
 **/
@Slf4j
@Service
public class ZzhOrderServiceImpl implements ZzhOrderService {
    @Autowired
    private ZzhOrderMapper orderMapper;

    @Override
    public void saveOrder(String orderNum, Integer price) {
        log.info("新增订单,{}",orderNum);
        ZzhOrder zzhOrder = new ZzhOrder().setId(UUID.fastUUID().toString(false));
        zzhOrder.setOrderNum(orderNum).setPrice(price).setOrderName("name").setCrtTime(new Date()).setCrtName("zzh");
        orderMapper.insert(zzhOrder);

        if (StringUtils.equals(orderNum,"3")) {
            throw new BaseException("订单编号不能为3");
        }
    }
}
