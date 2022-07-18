package org.liu.product;

import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * @Author lzs
 * @Date 2022/3/17 17:51
 **/
@EnableRabbit
//此服务没有引入其他微服务的feign模块
//@EnableFeignClients(basePackages = {"org.liu.user.feign.client"})
@SpringCloudApplication
@ComponentScan(basePackages = {"org.liu"})
public class CloudProductApplication {
    public static void main(String[] args) {
        SpringApplication.run(CloudProductApplication.class, args);
    }
}
