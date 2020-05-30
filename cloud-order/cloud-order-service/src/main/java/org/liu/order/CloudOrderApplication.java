package org.liu.order;

import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

@EnableRabbit
@EnableFeignClients(basePackages = "org.liu.user.feign.client")
@EnableCircuitBreaker
@EnableDiscoveryClient
@SpringBootApplication
@ComponentScan(basePackages = {"org.liu"})
public class CloudOrderApplication {

	public static void main(String[] args) {
		SpringApplication.run(CloudOrderApplication.class, args);
	}

}
