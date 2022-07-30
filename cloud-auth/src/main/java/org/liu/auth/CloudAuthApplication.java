package org.liu.auth;

import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

/**
 * @Author lzs
 * @Date 2022/3/17 17:18
 **/
@EnableFeignClients(basePackages = {"org.liu.admin.feign.client"})//引入外部的feignClient才需要这个注解
@SpringCloudApplication
@ComponentScan(basePackages = {"org.liu"})
public class CloudAuthApplication {

    public static void main(String[] args) {
        SpringApplication.run(CloudAuthApplication.class, args);
    }

}
