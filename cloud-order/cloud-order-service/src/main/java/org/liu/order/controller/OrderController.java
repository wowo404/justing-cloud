package org.liu.order.controller;

import lombok.RequiredArgsConstructor;
import org.justing.commons.model.Response;
import org.justing.commons.util.CollectionConverterUtil;
import org.liu.order.feign.pojo.AddOrderReq;
import org.liu.order.feign.pojo.AddOrderResp;
import org.liu.order.feign.pojo.OrderListResp;
import org.liu.order.pojo.Order;
import org.liu.order.service.OrderService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RequiredArgsConstructor
@RequestMapping("order")
@RestController
public class OrderController {

    @Value("${microservice.global.config.require}")
    private String globalConfig;//从全局配置文件中读取，即application.yml
    @Value("${custom.id}")
    private String globalProfileConfig;//从全局的根据环境区分的配置文件中读取，如dev环境是application-dev.yml
    private final OrderService orderService;

    @GetMapping("queryByUserId/{userId}")
    public Response<List<OrderListResp>> queryByUserId(@PathVariable("userId") Long userId){
        //从数据库或者其他地方获取到Order对象列表
        Order order = new Order();
        order.setOrderId(1L);
        order.setAmount(new BigDecimal("125.1"));
        order.setItemNumber(2);
        order.setUserId(userId);
        order.setCreateTime(new Date());

        List<Order> list = new ArrayList<>();
        list.add(order);

        //转成对外部接口开放的对象
        return Response.ok(CollectionConverterUtil.copyProperties(list, OrderListResp::new));
    }

    @PostMapping("addOrder")
    public Response<AddOrderResp> addOrder(@RequestBody AddOrderReq req){
        Order order = orderService.addOrder(req);
        AddOrderResp resp = new AddOrderResp();
        BeanUtils.copyProperties(order, resp);
        return Response.ok(resp);
    }

    @GetMapping("testConfig")
    public String testConfig(){
        return globalConfig + globalProfileConfig;
    }

}
