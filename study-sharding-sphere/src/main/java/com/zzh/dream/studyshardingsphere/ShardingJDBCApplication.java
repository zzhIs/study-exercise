package com.zzh.dream.studyshardingsphere;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author ：zzh
 * @date ：Created in 2022/3/10
 * @description:
 **/

@MapperScan("com.zzh.dream.studyshardingsphere")
@SpringBootApplication
public class ShardingJDBCApplication {
    public static void main(String[] args) {
        SpringApplication.run(ShardingJDBCApplication.class,args);
    }
}
