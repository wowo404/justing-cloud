package org.liu.order.feign.client;

import feign.Param;
import feign.RequestLine;
import org.justing.commons.model.Response;
import org.liu.common.core.constants.ServiceNameConstants;
import org.liu.common.feign.config.FeignConfig;
import org.liu.order.feign.client.fallback.OrderClientFallback;
import org.liu.order.feign.pojo.OrderListResp;
import org.springframework.cloud.openfeign.FeignClient;

import java.util.List;

@FeignClient(value = ServiceNameConstants.CLOUD_ORDER, fallback = OrderClientFallback.class, configuration = FeignConfig.class)
//@RequestMapping("order")//千万不要把此注解加上，不然就会出现Ambiguous mapping的错误
//updated at 20220509，出现此错误的原因很可能是当前service引入了自身的feignClient，现在已不会引入，所有没有这个问题了
public interface OrderClient {

    @RequestLine("GET /order/queryByUserId/{userId}")
    Response<List<OrderListResp>> queryByUserId(@Param("userId") Long userId);

}
