package org.liu.product.feign.client.fallback;

import org.justing.commons.model.Response;
import org.liu.product.feign.client.StockClient;
import org.liu.product.feign.pojo.req.EditStockReq;
import org.springframework.stereotype.Component;

/**
 * @Author lzs
 * @Date 2022/7/18 15:47
 **/
@Component
public class StockClientFallback implements StockClient {
    @Override
    public Response<Void> edit(EditStockReq req) {
        return Response.serverError();
    }
}
