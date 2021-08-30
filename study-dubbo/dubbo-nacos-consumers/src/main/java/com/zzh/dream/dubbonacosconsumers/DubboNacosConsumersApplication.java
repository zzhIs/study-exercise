package com.zzh.dream.dubbonacosconsumers;

import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
@EnableDubbo(scanBasePackages = "com.zzh.dream")
@SpringBootApplication
public class DubboNacosConsumersApplication {

	public static void main(String[] args) {
		SpringApplication.run(DubboNacosConsumersApplication.class, args);
	}

}
