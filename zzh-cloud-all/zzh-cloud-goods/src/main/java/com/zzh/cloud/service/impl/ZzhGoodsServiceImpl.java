package com.zzh.cloud.service.impl;

import cn.hutool.core.util.ObjectUtil;
import com.zzh.cloud.entity.ZzhGoods;
import com.zzh.cloud.exception.BaseException;
import com.zzh.cloud.feign.interf.order.ZzhOrderServiceFeign;
import com.zzh.cloud.mapper.ZzhGoodsMapper;
import com.zzh.cloud.result.CommonResult;
import com.zzh.cloud.service.ZzhGoodsService;
import io.seata.spring.annotation.GlobalTransactional;
import org.apache.commons.lang.StringUtils;
import org.apache.skywalking.apm.toolkit.trace.Tag;
import org.apache.skywalking.apm.toolkit.trace.Tags;
import org.apache.skywalking.apm.toolkit.trace.Trace;
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
    @Autowired
    private ZzhOrderServiceFeign orderServiceFeign;

    @Trace
    @Tags({@Tag(key = "param", value = "arg[0]"),
            @Tag(key = "result", value = "returnedObj")})
    @Override
    public ZzhGoods selectById(String id) {
        if (StringUtils.equals(id,"2")){
            throw new BaseException("id不能等于2...");
        }
        return goodsMapper.selectByPrimaryKey(id);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void countDownInventory(String id, Integer num)  {
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

    @Override
    @GlobalTransactional(name = "seataCountDownInventory",rollbackFor = Exception.class)
    public void seataCountDownInventory(String id, Integer num) {
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

        //todo 远程调用订单接口
        CommonResult commonResult = orderServiceFeign.saveOrder(id, num);

        if (commonResult.getCode() != 200) {
            throw new BaseException(String.format("远程调用订单接口...%s",commonResult.getMessage()));
        }
        System.out.println(commonResult);
    }
}
