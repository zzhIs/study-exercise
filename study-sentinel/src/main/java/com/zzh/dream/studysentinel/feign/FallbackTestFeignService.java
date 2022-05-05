package com.zzh.dream.studysentinel.feign;

import org.springframework.stereotype.Component;

/**
 * @description: TODO 类描述
 * @author: zhangzihao
 * @date: 27/04/2022
 **/
@Component
public class FallbackTestFeignService implements TestFeignService{

    @Override
    public String findOrderByUserId(Integer userId){
        return "服务降级了....";
    }
}
