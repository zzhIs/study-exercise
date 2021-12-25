package com.zzh.dream.studymongodb;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.MongoTemplate;

/**
 * @description: mongodb配置文件
 * @author: zhangzihao
 * @date: 24/12/2021
 **/
@Configuration
public class MongoConfig {

    /**
     * 指定连接地址
     *
     * @return
     */
    @Bean
    public MongoClient mongoClient() {
        return MongoClients.create("mongodb://127.0.0.1:27017");
    }

    /**
     * 配置操作模板方法和数据库
     *
     * @param mongoClient
     * @return
     */
    @Bean
    public MongoTemplate mongoTemplate(@Qualifier("mongoClient") MongoClient mongoClient) {
        return new MongoTemplate(mongoClient, "admin");
    }
}
