package org.liu.user.feign.client;

import org.justing.commons.model.Response;
import org.liu.common.core.constants.ServiceNameConstants;
import org.liu.user.feign.client.config.FeignConfig;
import org.liu.user.feign.client.fallback.UserClientFallback;
import org.liu.user.feign.pojo.BuyingBehaviorStatisticsReq;
import org.liu.user.feign.pojo.OperateAccountReq;
import org.liu.user.feign.pojo.UserResp;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(value = ServiceNameConstants.CLOUD_USER, fallback = UserClientFallback.class, configuration = FeignConfig.class)
public interface UserClient {

    @GetMapping("user/{userId}")
    Response<UserResp> getById(@PathVariable("userId") Long userId);

    @PostMapping("addBuyingBehaviorStatistics")
    Response<Void> addBuyingBehaviorStatistics(@RequestBody BuyingBehaviorStatisticsReq req);

    @PostMapping("operateAccount")
    Response<Void> operateAccount(@RequestBody OperateAccountReq req);
}
