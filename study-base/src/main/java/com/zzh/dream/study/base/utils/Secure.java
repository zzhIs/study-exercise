package com.zzh.dream.study.base.utils;

import cn.hutool.Hutool;
import cn.hutool.crypto.SecureUtil;
import org.springframework.jdbc.support.incrementer.PostgresSequenceMaxValueIncrementer;

/**
 * @description: TODO 类描述
 * @author: zhangzihao
 * @date: 30/09/2021
 **/
public class Secure {

    public static void main(String[] args) {
//        testSecureUtil();
        Hutool.printAllUtils();
    }


    public static void testSecureUtil(){
        String zhangzihao = SecureUtil.md5("zhangzihao");
        System.out.println(zhangzihao);
    }
}
