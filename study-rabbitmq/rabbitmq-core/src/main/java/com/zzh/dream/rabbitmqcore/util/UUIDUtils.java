package com.zzh.dream.rabbitmqcore.util;

import java.util.UUID;

/**
 * @description: uuid生成工具类
 * @author: zhangzihao
 * @date: 15/01/2022
 **/
public class UUIDUtils {
    public UUIDUtils() {
    }

    public static String get32UUID() {
        return UUID.randomUUID().toString().replace("-", "");
    }

    public static String getUUID() {
        return UUID.randomUUID().toString();
    }
}
