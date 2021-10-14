package org.liu.user.feign.client;

import feign.Param;
import feign.RequestLine;
import org.justing.commons.model.Response;
import org.liu.common.core.constants.ServiceNameConstants;
import org.liu.common.feign.config.FeignConfig;
import org.liu.user.feign.client.fallback.UserClientFallback;
import org.liu.user.feign.pojo.BuyingBehaviorStatisticsReq;
import org.liu.user.feign.pojo.OperateAccountReq;
import org.liu.user.feign.pojo.UserResp;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(value = ServiceNameConstants.CLOUD_USER, fallback = UserClientFallback.class, configuration = FeignConfig.class)
public interface UserClient {

    @RequestLine("GET /user/{userId}")
    Response<UserResp> getById(@Param("userId") Long userId);

    //参数默认就是json格式
    @RequestLine("POST /user/addBuyingBehaviorStatistics")
    Response<Void> addBuyingBehaviorStatistics(BuyingBehaviorStatisticsReq req);

    @RequestLine("POST /user/operateAccount")
    Response<Void> operateAccount(OperateAccountReq req);
}
