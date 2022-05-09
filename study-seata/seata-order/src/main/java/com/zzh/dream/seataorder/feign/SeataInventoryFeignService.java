package com.zzh.dream.seataorder.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @description: TODO 类描述
 * @author: zhangzihao
 * @date: 09/05/2022
 **/

@FeignClient(value = "seata-inventory",path = "/inventory")
public interface SeataInventoryFeignService {


    @PutMapping("/count_down")
    String countDownInventory(@RequestParam("goodsId") String goodsId) throws Exception ;

}
