package com.zzh.dream.dubbonacosprovider;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class DubboNacosProviderApplication {

	public static void main(String[] args) {
		SpringApplication.run(DubboNacosProviderApplication.class, args);
	}

}
