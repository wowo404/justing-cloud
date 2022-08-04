package org.liu.wx.feign.client;

import org.justing.commons.model.Response;
import org.liu.common.core.constants.ServiceNameConstants;
import org.liu.common.feign.config.FeignConfig;
import org.liu.wx.feign.client.fallback.WxUserClientFallback;
import org.liu.wx.feign.pojo.po.WxUser;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @Author lzs
 * @Date 2022/8/2 11:09
 **/
@FeignClient(value = ServiceNameConstants.CLOUD_USER, fallback = WxUserClientFallback.class, configuration = FeignConfig.class)
public interface WxUserClient {
    @GetMapping("wxUser/{openId}")
    Response<WxUser> queryByOpenId(@PathVariable("openId") String openId);

    @GetMapping("wxUser/{jsCode}")
    Response<WxUser> queryByJsCode(@PathVariable("jsCode") String jsCode);
}