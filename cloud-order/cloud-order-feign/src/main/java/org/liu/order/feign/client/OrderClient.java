package org.liu.order.feign.client;

import org.justing.commons.model.Response;
import org.liu.order.feign.client.config.FeignConfig;
import org.liu.order.feign.client.fallback.OrderClientFallback;
import org.liu.order.feign.pojo.OrderListResp;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(value = "cloud-order", fallback = OrderClientFallback.class, configuration = FeignConfig.class)
//@RequestMapping("order")//千万不要把此注解加上，不然就会出现Ambiguous mapping的错误
public interface OrderClient {

    @GetMapping("order/queryByUserId/{userId}")
    Response<List<OrderListResp>> queryByUserId(@PathVariable("userId") Long userId);

}
