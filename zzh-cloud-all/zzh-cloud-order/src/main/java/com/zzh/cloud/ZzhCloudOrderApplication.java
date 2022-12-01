package com.zzh.cloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
public class ZzhCloudOrderApplication {

	public static void main(String[] args) {
		SpringApplication.run(ZzhCloudOrderApplication.class, args);
	}

}
