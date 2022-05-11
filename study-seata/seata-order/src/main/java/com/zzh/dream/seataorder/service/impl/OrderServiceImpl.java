package com.zzh.dream.seataorder.service.impl;

import com.alibaba.nacos.common.utils.UuidUtils;
import com.zzh.dream.seataorder.entity.SeataOrder;
import com.zzh.dream.seataorder.feign.SeataInventoryFeignService;
import com.zzh.dream.seataorder.mapper.SeataOrderMapper;
import com.zzh.dream.seataorder.service.OrderService;
import io.seata.core.context.RootContext;
import io.seata.spring.annotation.GlobalTransactional;
import lombok.extern.slf4j.Slf4j;
import org.apache.skywalking.apm.toolkit.trace.Tag;
import org.apache.skywalking.apm.toolkit.trace.Tags;
import org.apache.skywalking.apm.toolkit.trace.Trace;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.UUID;

/**
 * @description: TODO 类描述
 * @author: zhangzihao
 * @date: 07/05/2022
 **/
@Slf4j
@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private SeataOrderMapper seataOrderMapper;
    @Autowired
    private SeataInventoryFeignService seataInventoryFeignService;

    /**
     * @Trace将方法加入追踪链路
     *  加入@Tags或@Tag记录参数和返回信息等
     * @param goodsIds
     * @throws Exception
     */
    @Trace
    @Tag(key = "param",value = "arg[0]")
    @Override
    @GlobalTransactional(name = "createOrder")
    public void saveOrder(String goodsIds) throws Exception {
        log.info("=============用户下单=================");
        log.info("当前 XID: {}", RootContext.getXID());
        // todo 新增订单
        int state = this.insert(goodsIds);
        log.info("下单执行完成，执行状态是{}",state);

        //todo 扣减库存
        seataInventoryFeignService.countDownInventory(goodsIds);

    }


    private int insert(String goodsIds){
        SeataOrder seataOrder = new SeataOrder().setGoodsId(goodsIds).setId(UuidUtils.generateUuid()).setOrderName("name" + UUID.randomUUID()).setCrtTime(new Date()).setStatus("0");
        return seataOrderMapper.insert(seataOrder);
    }
}
