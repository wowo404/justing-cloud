package org.liu.storage.feign.client;

import feign.RequestLine;
import org.justing.commons.model.Response;
import org.liu.common.core.constants.ServiceNameConstants;
import org.liu.common.feign.config.FeignConfig;
import org.liu.storage.feign.client.fallback.StorageClientFallback;
import org.liu.storage.feign.pojo.OperateStorageReq;
import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(value = ServiceNameConstants.CLOUD_STORAGE, fallback = StorageClientFallback.class, configuration = FeignConfig.class)
public interface StorageClient {
    @RequestLine("POST /storage/operate")
    Response<Void> operate(OperateStorageReq req);
}
