package org.liu.wx.feign.client.fallback;

import org.justing.commons.model.Response;
import org.liu.wx.feign.client.UserClient;
import org.liu.wx.feign.pojo.req.BuyingBehaviorStatisticsReq;
import org.liu.wx.feign.pojo.req.OperateAccountReq;
import org.liu.wx.feign.pojo.resp.UserResp;
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
