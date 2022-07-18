package org.liu.product.feign.client;

import org.liu.common.core.constants.ServiceNameConstants;
import org.liu.common.feign.config.FeignConfig;
import org.liu.product.feign.client.fallback.SpuClientFallback;
import org.springframework.cloud.openfeign.FeignClient;

/**
 * @Author lzs
 * @Date 2022/7/18 15:40
 **/
@FeignClient(name = ServiceNameConstants.CLOUD_PRODUCT, fallback = SpuClientFallback.class, configuration = FeignConfig.class)
public interface SpuClient {

}