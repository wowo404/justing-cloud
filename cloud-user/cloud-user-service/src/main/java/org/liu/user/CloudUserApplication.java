package org.liu.user;

import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;

@EnableRabbit
@EnableFeignClients(basePackages = {"org.liu.order.feign.client"})//引入外部的feignClient才需要这个注解
@SpringCloudApplication//集合了SpringBootApplication，EnableDiscoveryClient，EnableCircuitBreaker三个注解
//这里需要重新添加ComponentScan注解，因为引用的外部的feign客户端的fallback类被@Component注解，需要被扫描到
//如果feign的pojo的目录放再feign这个工程下，则要加上excludeFilters = {@ComponentScan.Filter(type = FilterType.ASPECTJ, pattern = {"org.liu.user.feign..*"})}，
// 表示本服务对外提供的feign客户端不需要被自身扫描到
@ComponentScan(basePackages = {"org.liu"})
public class CloudUserApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(CloudUserApplication.class, args);
        String[] beans = context.getBeanDefinitionNames();
        StringBuilder sb = new StringBuilder();
        for (String beanName : beans) {
            sb.append(beanName + "\n");
        }
//    	System.out.println("受spring托管的类：\n" + sb.toString());
    }

}
