package org.liu.admin.feign.client;

import org.justing.commons.model.Response;
import org.liu.admin.feign.client.fallback.OperatorClientFallback;
import org.liu.admin.feign.pojo.resp.OperatorDetailResp;
import org.liu.common.core.constants.ServiceNameConstants;
import org.liu.common.feign.config.FeignConfig;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(contextId = "OperatorClient", value = ServiceNameConstants.CLOUD_ADMIN, fallback = OperatorClientFallback.class, configuration = FeignConfig.class)
public interface OperatorClient {
    @GetMapping("operator/queryByUsername/{username}")
    Response<OperatorDetailResp> queryByUsername(@PathVariable("username") String username);
}
