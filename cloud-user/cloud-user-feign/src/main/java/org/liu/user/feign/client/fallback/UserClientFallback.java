package org.liu.user.feign.client.fallback;

import org.justing.commons.model.Response;
import org.liu.user.feign.client.UserClient;
import org.liu.user.feign.pojo.BuyingBehaviorStatisticsReq;
import org.liu.user.feign.pojo.OperateAccountReq;
import org.liu.user.feign.pojo.UserResp;
import org.springframework.stereotype.Component;

@Component
public class UserClientFallback implements UserClient {
    @Override
    public Response<UserResp> getById(Long userId) {
        return Response.serverError();
    }

    @Override
    public Response<Void> addBuyingBehaviorStatistics(BuyingBehaviorStatisticsReq req) {
        return Response.serverError();
    }

    @Override
    public Response<Void> operateAccount(OperateAccountReq req) {
        return Response.serverError();
    }
}
