package com.zzh.dream.seatainventory;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(scanBasePackages = "com.zzh.dream",exclude = DataSourceAutoConfiguration.class)
public class SeataInventoryApplication {

	public static void main(String[] args) {
		SpringApplication.run(SeataInventoryApplication.class, args);
	}

}
