package com.zzh.dream.study.base;

import com.zzh.starter.EnableMyStarter;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@EnableMyStarter
@EnableAspectJAutoProxy
@ComponentScan({"com.zzh.dream"})
@MapperScan(value = "com.zzh.dream.study.base.mapper")
@SpringBootApplication
public class StudyBaseApplication {

	public static void main(String[] args) {
		SpringApplication.run(StudyBaseApplication.class, args);
	}

}
