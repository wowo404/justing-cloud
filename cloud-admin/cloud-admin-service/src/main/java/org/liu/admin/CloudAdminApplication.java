package org.liu.admin;

import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * @Author lzs
 * @Date 2022/7/18 15:20
 **/
@EnableRabbit
@SpringCloudApplication
@ComponentScan(basePackages = {"org.liu"})
public class CloudAdminApplication {

    public static void main(String[] args) {
        SpringApplication.run(CloudAdminApplication.class, args);
    }

}
