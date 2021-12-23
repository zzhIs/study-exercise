package com.zzh.dream.zookeeperclinet;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class ZookeeperClinetApplication {

	public static void main(String[] args) {
		SpringApplication.run(ZookeeperClinetApplication.class, args);
	}

}
