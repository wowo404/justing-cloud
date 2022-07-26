package org.liu.wx.feign.client;

import org.justing.commons.model.Response;
import org.liu.common.core.constants.ServiceNameConstants;
import org.liu.common.feign.config.FeignConfig;
import org.liu.wx.feign.client.fallback.UserClientFallback;
import org.liu.wx.feign.pojo.req.BuyingBehaviorStatisticsReq;
import org.liu.wx.feign.pojo.req.OperateAccountReq;
import org.liu.wx.feign.pojo.resp.UserResp;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(value = ServiceNameConstants.CLOUD_USER, fallback = UserClientFallback.class, configuration = FeignConfig.class)
public interface UserClient {

    @GetMapping("/user/{userId}")
    Response<UserResp> getById(@PathVariable("userId") Long userId);

    @PostMapping("/user/addBuyingBehaviorStatistics")
    Response<Void> addBuyingBehaviorStatistics(@RequestBody BuyingBehaviorStatisticsReq req);

    @PostMapping("/user/operateAccount")
    Response<Void> operateAccount(@RequestBody OperateAccountReq req);
}
