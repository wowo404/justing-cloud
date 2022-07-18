package org.liu.storage.feign.client;

import org.justing.commons.model.Response;
import org.liu.common.core.constants.ServiceNameConstants;
import org.liu.common.feign.config.FeignConfig;
import org.liu.storage.feign.client.fallback.StorageClientFallback;
import org.liu.storage.feign.pojo.OperateStorageReq;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(value = ServiceNameConstants.CLOUD_STORAGE, fallback = StorageClientFallback.class, configuration = FeignConfig.class)
public interface StorageClient {
    @PostMapping("/storage/operate")
    Response<Void> operate(@RequestBody OperateStorageReq req);
}
