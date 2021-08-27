package com.zzh.dream.nacosconsumers;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;


@EnableDiscoveryClient
@SpringBootApplication
public class NacosConsumersApplication {

	public static void main(String[] args) {
		SpringApplication.run(NacosConsumersApplication.class, args);
	}






}
