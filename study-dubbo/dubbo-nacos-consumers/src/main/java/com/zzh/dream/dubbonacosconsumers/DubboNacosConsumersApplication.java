package com.zzh.dream.dubbonacosconsumers;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class DubboNacosConsumersApplication {

	public static void main(String[] args) {
		SpringApplication.run(DubboNacosConsumersApplication.class, args);
	}

}
