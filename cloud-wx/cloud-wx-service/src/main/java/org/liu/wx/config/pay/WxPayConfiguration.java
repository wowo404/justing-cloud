package org.liu.wx.config.pay;

import com.github.binarywang.wxpay.config.WxPayConfig;
import com.github.binarywang.wxpay.service.WxPayService;
import com.github.binarywang.wxpay.service.impl.WxPayServiceImpl;
import com.google.common.collect.Maps;
import lombok.extern.slf4j.Slf4j;
import org.justing.commons.exception.CommonException;
import org.liu.wx.feign.pojo.po.WxApp;
import org.liu.wx.service.WxAppService;
import org.springframework.context.annotation.Configuration;

import java.util.Map;

import static org.liu.wx.feign.pojo.exception.BizCodeEnum.MISSING_PAY_CONFIG;

/**
 * @Author lzs
 * @Date 2022/8/5 9:20
 **/
@Slf4j
@Configuration
public class WxPayConfiguration {

    private static Map<String, WxPayService> payServiceMap = Maps.newHashMap();

    private static WxAppService wxAppService;

    public WxPayConfiguration(WxAppService wxAppService) {
        WxPayConfiguration.wxAppService = wxAppService;
    }

    public static WxPayService getPayService(String appId) {
        WxPayService wxPayService = payServiceMap.get(appId);
        if (null == wxPayService) {
            WxApp wxApp = wxAppService.queryByAppId(appId);
            if (null == wxApp) {
                throw new CommonException(MISSING_PAY_CONFIG);
            }
            WxPayConfig payConfig = new WxPayConfig();
            payConfig.setAppId(wxApp.getAppId());
            payConfig.setMchId(wxApp.getMchId());
            payConfig.setMchKey(wxApp.getMchKey());
            payConfig.setKeyPath(wxApp.getKeyPath());
            //weixin-java-pay没有提供okhttp的方式
            wxPayService = new WxPayServiceImpl();
            wxPayService.setConfig(payConfig);
        }
        return wxPayService;
    }

    public static void remove(String appId) {
        payServiceMap.remove(appId);
    }

}
