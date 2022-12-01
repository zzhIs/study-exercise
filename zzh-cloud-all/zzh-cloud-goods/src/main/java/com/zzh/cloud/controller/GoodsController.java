package com.zzh.cloud.controller;

import cn.hutool.core.util.ObjectUtil;
import com.zzh.cloud.configuration.UserConfigInfo;
import com.zzh.cloud.entity.ZzhGoods;
import com.zzh.cloud.result.CommonResult;
import com.zzh.cloud.service.ZzhGoodsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.BoundHashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

import static com.zzh.cloud.constant.RedisKeyConstant.GOODS_CACHE;

/**
 * @description: 基础控制器
 * @author: zhangzihao
 * @date: 26/08/2021
 **/
@Slf4j
@RestController
@RequestMapping("/goods")
public class GoodsController {
    @Autowired
    private ZzhGoodsService goodsService;

    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private UserConfigInfo user;


    @GetMapping("/{id}")
    public CommonResult selectById(@PathVariable("id")String id) {

        return CommonResult.success(goodsService.selectById(id));
    }

    @PutMapping("/{id}")
    public CommonResult deduce(@PathVariable("id")String id,
                         @RequestParam("num")Integer num) throws Exception {
        goodsService.countDownInventory(id,num);
        return CommonResult.success("success");
    }

    @PutMapping("/cache/{id}")
    public CommonResult selectCacheInfo(@PathVariable("id")String id){
        BoundHashOperations goodsOperation = redisTemplate.boundHashOps(GOODS_CACHE);
        ZzhGoods goods = null;
        if (goodsOperation.hasKey(id)) {
            log.info("缓存存在,从缓存中获取....");
            goods = (ZzhGoods) goodsOperation.get(id);
        }else{
            log.info("缓存不存在,从数据库中获取....");
            goods = goodsService.selectById(id);
            if (ObjectUtil.isNotNull(goods)) {
                goodsOperation.put(id,goods);
            }
        }
        return CommonResult.success(goods);
    }


    @PutMapping("/user")
    public CommonResult selectUserInfo(){
        return CommonResult.success(String.format("姓名：%s,年龄：%s",user.getName(),user.getAge()));
    }
}
