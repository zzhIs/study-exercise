package com.zzh.dream.dubbonacosprovider;

import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

//@EnableDiscoveryClient
@EnableDubbo(scanBasePackages = "com.zzh.dream")
@SpringBootApplication
public class DubboNacosProviderApplication {

	public static void main(String[] args) {
		SpringApplication.run(DubboNacosProviderApplication.class, args);
	}

}
