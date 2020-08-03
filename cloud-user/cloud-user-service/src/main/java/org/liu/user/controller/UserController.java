package org.liu.user.controller;

import org.justing.commons.model.Response;
import org.liu.order.feign.client.OrderClient;
import org.liu.order.feign.pojo.OrderListResp;
import org.liu.user.feign.pojo.BuyingBehaviorStatisticsReq;
import org.liu.user.feign.pojo.OperateAccountReq;
import org.liu.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("user")
@RestController
//使用了Feign后也不需要这个配置了
//@DefaultProperties(defaultFallback = "defaultFallback")//defaultFallback方法要和熔断的方法同返回值类型，不需要参数
public class UserController {

    @Autowired
    private OrderClient orderClient;
    @Autowired
    private UserService userService;

    @GetMapping("myOrder/{userId}")
    public Response<List<OrderListResp>> myOrder(@PathVariable("userId") Long userId){
        //从会话中拿到当前登录用户的ID？？？微服务中有没有会话这个概念？？？
        userId = 1L;
        return orderClient.queryByUserId(userId);
    }

    @PostMapping("addBuyingBehaviorStatistics")
    public Response<Void> addBuyingBehaviorStatistics(@RequestBody BuyingBehaviorStatisticsReq req){
        userService.addBuyingBehaviorStatistics(req);
        return Response.ok();
    }

    @PostMapping("operateAccount")
    public Response<Void> operateAccount(@RequestBody OperateAccountReq req){
        userService.operateAccount(req);
        return Response.ok();
    }

    //---------------------------------注释掉的代码仅保留用来学习------------------
/*
//使用了feign之后，也不需要手动调用restTemplate了
    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private DiscoveryClient discoveryClient;

    //在未使用eureka服务发现时，url必须指定某一台服务器地址
    @GetMapping("myOrder1")
    public List<OrderListResp> myOrder1(){
        Long userId = 1L;
        String url = "http://127.0.0.1:7412/order/queryByUserId/{userId}";
        OrderListResp[] order = restTemplate.getForObject(url, OrderListResp[].class, userId);
        return Arrays.asList(order);
    }

    //使用了eureka服务发现，可以从服务发现中查询出所有的服务列表，但未使用ribbon做负载均衡，还是只能手动选择某一台发送请求
    @GetMapping("myOrder2")
    public List<OrderListResp> myOrder2(){
        List<ServiceInstance> instances = discoveryClient.getInstances("cloud-order");
        ServiceInstance serviceInstance = instances.get(0);
        String url = "http://" + serviceInstance.getHost() + ":" + serviceInstance.getPort() + "/order/queryByUserId/{userId}";

        //从会话中拿到当前登录用户的ID
        Long userId = 1L;
        OrderListResp[] order = restTemplate.getForObject(url, OrderListResp[].class, userId);
        return Arrays.asList(order);
    }

    //使用了eureka和ribbon，不再需要指定具体服务器地址了
    @GetMapping("myOrder3")
    @HystrixCommand//HystrixCommand中的fallbackMethod属性没写时，使用DefaultProperties中的配置，配置了fallbackMethod优先，fallbackMethod配置的方法要熔断的方法同返回值类型同参数
    public List<OrderListResp> myOrder3(){
        //从会话中拿到当前登录用户的ID
        Long userId = 1L;
        //cloud-order是spring.application.name，即注册到eureka的名字
        String url = "http://cloud-order/order/queryByUserId/{userId}";
        OrderListResp[] order = restTemplate.getForObject(url, OrderListResp[].class, userId);
        return Arrays.asList(order);
    }*/

}
