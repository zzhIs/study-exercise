package com.zzh.dream.dubbonacosapi.api;

import com.zzh.dream.dubbonacosapi.entity.Product;

import java.util.List;

/**
 * @description: 商品类接口
 * @author: zhangzihao
 * @date: 30/08/2021
 **/
public interface ProductService {

    /**
     * 查询商品列表
     *
     * @return
     */
    List<Product> selectProductList();
}
