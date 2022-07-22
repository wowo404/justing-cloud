package org.liu.product.feign.client;

import org.justing.commons.model.Response;
import org.liu.common.core.constants.ServiceNameConstants;
import org.liu.common.feign.config.FeignConfig;
import org.liu.product.feign.client.fallback.SpuClientFallback;
import org.liu.product.feign.pojo.EditStockReq;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * @Author lzs
 * @Date 2022/7/18 15:46
 **/
@FeignClient(name = ServiceNameConstants.CLOUD_PRODUCT, fallback = SpuClientFallback.class, configuration = FeignConfig.class)
public interface StockClient {
    @PostMapping("/stock/edit")
    Response<Void> edit(@RequestBody EditStockReq req);
}