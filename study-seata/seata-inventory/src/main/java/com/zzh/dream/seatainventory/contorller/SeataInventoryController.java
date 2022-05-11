package com.zzh.dream.seatainventory.contorller;

import com.zzh.dream.seatainventory.service.SeataInventoryService;
import lombok.extern.slf4j.Slf4j;
import org.apache.skywalking.apm.toolkit.trace.TraceContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @description: TODO 类描述
 * @author: zhangzihao
 * @date: 09/05/2022
 **/
@Slf4j
@RequestMapping("/inventory")
@RestController
public class SeataInventoryController {

    @Autowired
    private SeataInventoryService seataInventoryService;

    @PutMapping("/count_down")
    public String countDownInventory(@RequestParam("goodsId") String goodsId) throws Exception {
        String traceId = TraceContext.traceId();
        log.info("库存服务--跟踪的tranceID是{}",traceId);
        seataInventoryService.countDownInventory(goodsId);
        return "扣减库存成功..."+goodsId;
    }
}
