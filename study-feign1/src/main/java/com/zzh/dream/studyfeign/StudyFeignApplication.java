package com.zzh.dream.studyfeign;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication
public class StudyFeignApplication {

	public static void main(String[] args) {
		SpringApplication.run(StudyFeignApplication.class, args);
	}

}
