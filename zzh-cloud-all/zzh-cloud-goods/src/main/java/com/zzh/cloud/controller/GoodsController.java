package com.zzh.cloud.controller;

import com.zzh.cloud.entity.ZzhGoods;
import com.zzh.cloud.exception.CommonResult;
import com.zzh.cloud.service.ZzhGoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @description: 基础控制器
 * @author: zhangzihao
 * @date: 26/08/2021
 **/
@RestController
@RequestMapping("/goods")
public class GoodsController {
    @Autowired
    private ZzhGoodsService goodsService;

    @GetMapping("/{id}")
    public CommonResult insert(@PathVariable("id")String id) {

        return CommonResult.success(goodsService.selectById(id));
    }

    @PutMapping("/{id}")
    public CommonResult insert(@PathVariable("id")String id,
                         @RequestParam("num")Integer num) throws Exception {
        goodsService.countDownInventory(id,num);
        return CommonResult.success("success");
    }
}
