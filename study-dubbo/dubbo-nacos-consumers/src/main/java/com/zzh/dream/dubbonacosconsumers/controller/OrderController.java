package com.zzh.dream.dubbonacosconsumers.controller;

import com.zzh.dream.dubbonacosapi.api.ProductService;
import com.zzh.dream.dubbonacosapi.entity.Product;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @description: TODO 类描述
 * @author: zhangzihao
 * @date: 30/08/2021
 **/
@RestController
@RequestMapping("/order")
public class OrderController {
    @DubboReference
    private ProductService productService;

    @GetMapping("/list")
    public List<Product> getProductList(){
        return productService.selectProductList();
    }
}
