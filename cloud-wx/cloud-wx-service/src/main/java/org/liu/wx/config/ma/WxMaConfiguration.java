package org.liu.wx.config.ma;

import cn.binarywang.wx.miniapp.api.WxMaService;
import cn.binarywang.wx.miniapp.api.impl.WxMaServiceOkHttpImpl;
import cn.binarywang.wx.miniapp.config.impl.WxMaRedisBetterConfigImpl;
import cn.binarywang.wx.miniapp.message.WxMaMessageRouter;
import com.google.common.collect.Maps;
import me.chanjar.weixin.common.redis.RedisTemplateWxRedisOps;
import me.chanjar.weixin.common.redis.WxRedisOps;
import me.chanjar.weixin.open.api.WxOpenService;
import org.justing.commons.exception.CommonException;
import org.liu.common.service.tenant.TenantContextHolder;
import org.liu.wx.feign.pojo.enums.IsComponentEnum;
import org.liu.wx.feign.pojo.po.WxApp;
import org.liu.wx.service.WxAppService;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.StringRedisTemplate;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

import static org.liu.wx.feign.pojo.exception.BizCodeEnum.MISSING_MINIAPP_CONFIG;

/**
 * 小程序Configuration
 *
 * @author JL
 */
@Configuration
public class WxMaConfiguration {
    /**
     * 全局缓存WxMaService
     */
    private static Map<String, WxMaService> maServices = Maps.newHashMap();

    /**
     * 全局缓存WxMaMessageRouter
     */
    private static Map<String, WxMaMessageRouter> routers = Maps.newHashMap();

    private static StringRedisTemplate stringRedisTemplate;
    private static WxAppService wxAppService;
    private static WxOpenService wxOpenService;

    public WxMaConfiguration(StringRedisTemplate stringRedisTemplate, WxAppService wxAppService, WxOpenService wxOpenService) {
        WxMaConfiguration.stringRedisTemplate = stringRedisTemplate;
        WxMaConfiguration.wxAppService = wxAppService;
        WxMaConfiguration.wxOpenService = wxOpenService;
    }

    public static WxMaService getMaService(HttpServletRequest request) {
        String appId = getAppId(request);
        return getMaService(appId);
    }

    /**
     * 获取全局缓存WxMaService
     *
     * @param appId
     * @return
     */
    public static WxMaService getMaService(String appId) {
        WxMaService wxMaService = maServices.get(appId);
        if (wxMaService == null) {
            WxApp wxApp = wxAppService.queryByAppId(appId);
            if (wxApp == null) {
                throw new CommonException(MISSING_MINIAPP_CONFIG, appId);
            }
            if (IsComponentEnum.YES.getCode().equals(wxApp.getIsComponent())) {//第三方平台
                wxMaService = wxOpenService.getWxOpenComponentService().getWxMaServiceByAppid(appId);
                maServices.put(appId, wxMaService);
                routers.put(appId, newRouter(wxMaService));
            } else {
                WxRedisOps redisOps = new RedisTemplateWxRedisOps(stringRedisTemplate);
                WxMaRedisBetterConfigImpl config = new WxMaRedisBetterConfigImpl(redisOps, "ma");
                config.setAppid(wxApp.getAppId());
                config.setSecret(wxApp.getSecret());
                config.setToken(wxApp.getToken());
                config.setAesKey(wxApp.getAesKey());
                wxMaService = new WxMaServiceOkHttpImpl();
                wxMaService.setWxMaConfig(config);
                maServices.put(appId, wxMaService);
                routers.put(appId, newRouter(wxMaService));
            }
        }
        return wxMaService;
    }

    /**
     * 移除WxMaService缓存
     *
     * @param appId
     */
    public static void removeWxMaService(String appId) {
        maServices.remove(appId);
        routers.remove(appId);
    }

    private static WxMaMessageRouter newRouter(WxMaService service) {
        return new WxMaMessageRouter(service);
    }

    /**
     * 通过request获取appId
     *
     * @param request
     * @return
     * @throws Exception
     */
    public static String getAppId(HttpServletRequest request) {
        String appId = request.getHeader("app-id");
        return appId;
    }

    /**
     * 通过request获取WxApp
     *
     * @param request
     * @return
     */
    public static WxApp getApp(HttpServletRequest request) {
        String appId = getAppId(request);
        WxApp wxApp = wxAppService.queryByAppId(appId);
        if (wxApp == null) {
            throw new CommonException(MISSING_MINIAPP_CONFIG, appId);
        }
        TenantContextHolder.setTenantId(wxApp.getTenantId());//指定租户ID
        return wxApp;
    }

}
