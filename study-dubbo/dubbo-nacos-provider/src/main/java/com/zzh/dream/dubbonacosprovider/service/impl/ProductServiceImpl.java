package com.zzh.dream.dubbonacosprovider.service.impl;

import com.google.common.collect.Lists;
import com.zzh.dream.dubbonacosapi.api.ProductService;
import com.zzh.dream.dubbonacosapi.entity.Product;
import org.apache.dubbo.config.annotation.Service;

import java.util.List;

/**
 * @description: 服务提供者
 * @author: zhangzihao
 * @date: 30/08/2021
 **/
@Service
public class ProductServiceImpl implements ProductService {
    @Override
    public List<Product> selectProductList() {
        return Lists.newArrayList(new Product(1,"name",1000,45.55));
    }
}
