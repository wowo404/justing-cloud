package org.liu.order.feign.client.fallback;

import org.justing.commons.model.Response;
import org.liu.order.feign.client.OrderClient;
import org.liu.order.feign.pojo.OrderListResp;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class OrderClientFallback implements OrderClient {
    @Override
    public Response<List<OrderListResp>> queryByUserId(Long userId) {
        return Response.serverError();
    }
}
