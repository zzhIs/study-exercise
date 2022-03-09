package com.zzh.dream.rabbitmqproducer;

import com.zzh.dream.rabbitmqcore.annotation.EnableZzhMq;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@EnableZzhMq
@EnableAspectJAutoProxy
@ComponentScan({"com.zzh.dream"})
@SpringBootApplication
public class RabbitmqProducerApplication {

	public static void main(String[] args) {
		SpringApplication.run(RabbitmqProducerApplication.class, args);
	}

}
