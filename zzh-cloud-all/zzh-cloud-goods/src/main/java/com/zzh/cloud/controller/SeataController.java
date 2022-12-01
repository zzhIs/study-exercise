package com.zzh.cloud.controller;

import com.zzh.cloud.result.CommonResult;
import com.zzh.cloud.service.ZzhGoodsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @description: skywalking控制器
 * @author: zhangzihao
 * @date: 26/08/2021
 **/
@Slf4j
@RestController
@RequestMapping("/goods")
public class SeataController {
    @Autowired
    private ZzhGoodsService goodsService;


    @GetMapping("/seata/{id}")
    public CommonResult countDownInventory(@PathVariable("id")String id, @RequestParam("num")Integer num) {
        goodsService.seataCountDownInventory(id,num);
        return CommonResult.success("success");
    }
}
