package org.liu.order;

import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

@EnableRabbit
@EnableFeignClients(basePackages = {"org.liu.wx.feign.client", "org.liu.product.feign.client"})
@SpringCloudApplication
@ComponentScan(basePackages = {"org.liu"})
public class CloudOrderApplication {

	public static void main(String[] args) {
		SpringApplication.run(CloudOrderApplication.class, args);
	}

}
