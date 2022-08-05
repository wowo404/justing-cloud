package org.liu.wx.feign.client.fallback;

import org.justing.commons.model.Response;
import org.liu.wx.feign.client.WxUserClient;
import org.liu.wx.feign.pojo.po.WxUser;
import org.springframework.stereotype.Component;

/**
 * @Author lzs
 * @Date 2022/8/2 11:10
 **/
@Component
public class WxUserClientFallback implements WxUserClient {
    @Override
    public Response<WxUser> queryByOpenId(String openId) {
        return Response.serverError();
    }

    @Override
    public Response<WxUser> queryByJsCode(String jsCode) {
        return Response.serverError();
    }
}
