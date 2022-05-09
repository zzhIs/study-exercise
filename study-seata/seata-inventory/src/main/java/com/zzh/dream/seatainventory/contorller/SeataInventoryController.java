package com.zzh.dream.seatainventory.contorller;

import com.zzh.dream.seatainventory.service.SeataInventoryService;
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
@RequestMapping("/inventory")
@RestController
public class SeataInventoryController {

    @Autowired
    private SeataInventoryService seataInventoryService;

    @PutMapping("/count_down")
    public String countDownInventory(@RequestParam("goodsId") String goodsId) throws Exception {
        seataInventoryService.countDownInventory(goodsId);
        return "扣减库存成功..."+goodsId;
    }
}
