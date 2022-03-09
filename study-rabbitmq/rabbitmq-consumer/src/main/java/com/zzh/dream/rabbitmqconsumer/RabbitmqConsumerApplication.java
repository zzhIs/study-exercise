package com.zzh.dream.rabbitmqconsumer;

import com.zzh.dream.rabbitmqcore.annotation.EnableZzhMq;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@EnableZzhMq
@EnableAspectJAutoProxy
@ComponentScan({"com.zzh.dream"})
@SpringBootApplication
public class RabbitmqConsumerApplication {

	public static void main(String[] args) {
		SpringApplication.run(RabbitmqConsumerApplication.class, args);
	}

}
