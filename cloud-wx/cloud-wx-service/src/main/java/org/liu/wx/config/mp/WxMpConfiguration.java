/**
 * bs
 */
package org.liu.wx.config.mp;

import com.google.common.collect.Maps;
import lombok.extern.slf4j.Slf4j;
import me.chanjar.weixin.common.api.WxConsts.EventType;
import me.chanjar.weixin.common.api.WxConsts.MenuButtonType;
import me.chanjar.weixin.common.api.WxConsts.XmlMsgType;
import me.chanjar.weixin.common.redis.RedisTemplateWxRedisOps;
import me.chanjar.weixin.common.redis.WxRedisOps;
import me.chanjar.weixin.mp.api.WxMpMessageRouter;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.api.impl.WxMpServiceImpl;
import me.chanjar.weixin.mp.config.impl.WxMpRedisConfigImpl;
import me.chanjar.weixin.mp.constant.WxMpEventConstants;
import me.chanjar.weixin.open.api.WxOpenService;
import org.liu.wx.feign.pojo.enums.IsComponentEnum;
import org.liu.wx.feign.pojo.po.WxApp;
import org.liu.wx.handler.*;
import org.liu.wx.service.WxAppService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.StringRedisTemplate;

import java.util.Map;

/**
 * 公众号Configuration
 *
 * @author JL
 */
@Slf4j
@Configuration
public class WxMpConfiguration {
    /**
     * 全局缓存WxMpService
     */
    private static Map<String, WxMpService> mpServices = Maps.newHashMap();

    /**
     * 全局缓存WxMpMessageRouter
     */
    private static Map<String, WxMpMessageRouter> routers = Maps.newHashMap();

    private static WxOpenService wxOpenService;
    private static StringRedisTemplate redisTemplate;
    private static WxAppService wxAppService;
    private static LogHandler logHandler;
    private static NullHandler nullHandler;
    private static KfSessionHandler kfSessionHandler;
    private static StoreCheckNotifyHandler storeCheckNotifyHandler;
    private static LocationHandler locationHandler;
    private static MenuHandler menuHandler;
    private static MsgHandler msgHandler;
    private static UnSubscribeHandler unSubscribeHandler;
    private static SubscribeHandler subscribeHandler;
    private static MassMsgHandler massMsgHandler;
    private static UserGetCardHandler userGetCardHandler;
    private static UserDelCardHandler userDelCardHandler;
    private static UserActivateCardHandler userActivateCardHandler;

    @Autowired
    public WxMpConfiguration(LogHandler logHandler,
                             NullHandler nullHandler,
                             KfSessionHandler kfSessionHandler,
                             StoreCheckNotifyHandler storeCheckNotifyHandler,
                             LocationHandler locationHandler,
                             MenuHandler menuHandler,
                             MsgHandler msgHandler,
                             UnSubscribeHandler unSubscribeHandler,
                             SubscribeHandler subscribeHandler,
                             MassMsgHandler massMsgHandler,
                             UserGetCardHandler userGetCardHandler,
                             UserDelCardHandler userDelCardHandler,
                             UserActivateCardHandler userActivateCardHandler,
                             StringRedisTemplate redisTemplate,
                             WxAppService wxAppService,
                             WxOpenService wxOpenService) {
        WxMpConfiguration.logHandler = logHandler;
        WxMpConfiguration.nullHandler = nullHandler;
        WxMpConfiguration.kfSessionHandler = kfSessionHandler;
        WxMpConfiguration.storeCheckNotifyHandler = storeCheckNotifyHandler;
        WxMpConfiguration.locationHandler = locationHandler;
        WxMpConfiguration.menuHandler = menuHandler;
        WxMpConfiguration.msgHandler = msgHandler;
        WxMpConfiguration.unSubscribeHandler = unSubscribeHandler;
        WxMpConfiguration.subscribeHandler = subscribeHandler;
        WxMpConfiguration.massMsgHandler = massMsgHandler;
        WxMpConfiguration.userGetCardHandler = userGetCardHandler;
        WxMpConfiguration.userDelCardHandler = userDelCardHandler;
        WxMpConfiguration.userActivateCardHandler = userActivateCardHandler;
        WxMpConfiguration.redisTemplate = redisTemplate;
        WxMpConfiguration.wxAppService = wxAppService;
        WxMpConfiguration.wxOpenService = wxOpenService;
    }

    /**
     * 获取全局缓存WxMpService
     *
     * @param appId
     * @return
     */
    public static WxMpService getMpService(String appId) {
        WxMpService wxMpService = mpServices.get(appId);
        if (wxMpService == null) {
            WxApp wxApp = wxAppService.queryByAppId(appId);
            if (wxApp != null) {
                if (IsComponentEnum.YES.getCode().equals(wxApp.getIsComponent())) {//第三方授权账号
                    wxMpService = wxOpenService.getWxOpenComponentService().getWxMpServiceByAppid(appId);
                    mpServices.put(appId, wxMpService);
                    routers.put(appId, newRouter(wxMpService));
                } else {
                    WxRedisOps redisOps = new RedisTemplateWxRedisOps(redisTemplate);
                    WxMpRedisConfigImpl wxMpRedisConfig = new WxMpRedisConfigImpl(redisOps, "mp");
                    wxMpRedisConfig.setAppId(wxApp.getAppId());
                    wxMpRedisConfig.setSecret(wxApp.getSecret());
                    wxMpRedisConfig.setToken(wxApp.getToken());
                    wxMpRedisConfig.setAesKey(wxApp.getAesKey());
                    wxMpService = new WxMpServiceImpl();
                    wxMpService.setWxMpConfigStorage(wxMpRedisConfig);
                    mpServices.put(appId, wxMpService);
                    routers.put(appId, newRouter(wxMpService));
                }
            }
        }
        return wxMpService;
    }

    /**
     * 移除WxMpService缓存
     *
     * @param appId
     */
    public static void removeWxMpService(String appId) {
        mpServices.remove(appId);
        routers.remove(appId);
    }

    /**
     * 获取全局缓存WxMpMessageRouter
     *
     * @param appId
     * @return
     */
    public static WxMpMessageRouter getWxMpMessageRouter(String appId) {
        WxMpMessageRouter wxMpMessageRouter = routers.get(appId);
        return wxMpMessageRouter;
    }

    private static WxMpMessageRouter newRouter(WxMpService wxMpService) {
        final WxMpMessageRouter newRouter = new WxMpMessageRouter(wxMpService);

        // 记录所有事件的日志 （异步执行）
        newRouter.rule().handler(logHandler).next();

        // 接收客服会话管理事件
        newRouter.rule().async(false).msgType(XmlMsgType.EVENT)
                .event(WxMpEventConstants.CustomerService.KF_CREATE_SESSION)
                .handler(kfSessionHandler).end();
        newRouter.rule().async(false).msgType(XmlMsgType.EVENT)
                .event(WxMpEventConstants.CustomerService.KF_CLOSE_SESSION)
                .handler(kfSessionHandler)
                .end();
        newRouter.rule().async(false).msgType(XmlMsgType.EVENT)
                .event(WxMpEventConstants.CustomerService.KF_SWITCH_SESSION)
                .handler(kfSessionHandler).end();

        // 门店审核事件
        newRouter.rule().async(false).msgType(XmlMsgType.EVENT)
                .event(WxMpEventConstants.POI_CHECK_NOTIFY)
                .handler(storeCheckNotifyHandler).end();

        // 自定义菜单事件
        newRouter.rule().async(false).msgType(XmlMsgType.EVENT)
                .event(MenuButtonType.CLICK).handler(menuHandler).end();

        // 点击菜单连接事件
        newRouter.rule().async(false).msgType(XmlMsgType.EVENT)
                .event(MenuButtonType.VIEW).handler(menuHandler).end();

        // 扫码事件
        newRouter.rule().async(false).msgType(XmlMsgType.EVENT)
                .event(EventType.SCANCODE_WAITMSG).handler(menuHandler).end();

        // 关注事件
        newRouter.rule().async(false).msgType(XmlMsgType.EVENT)
                .event(EventType.SUBSCRIBE).handler(subscribeHandler)
                .end();

        // 取消关注事件
        newRouter.rule().async(false).msgType(XmlMsgType.EVENT)
                .event(EventType.UNSUBSCRIBE)
                .handler(unSubscribeHandler).end();

        // 上报地理位置事件
        newRouter.rule().async(false).msgType(XmlMsgType.EVENT)
                .event(EventType.LOCATION).handler(locationHandler)
                .end();

        // 卡券领取事件
        newRouter.rule().async(false).msgType(XmlMsgType.EVENT)
                .event(EventType.CARD_USER_GET_CARD).handler(userGetCardHandler).end();

        // 卡券删除事件
        newRouter.rule().async(false).msgType(XmlMsgType.EVENT)
                .event(EventType.CARD_USER_DEL_CARD).handler(userDelCardHandler).end();

        // 卡券激活事件
        newRouter.rule().async(false).msgType(XmlMsgType.EVENT)
                .event(EventType.CARD_SUBMIT_MEMBERCARD_USER_INFO).handler(userActivateCardHandler).end();

        // 群发回调事件
        newRouter.rule().async(false).msgType(XmlMsgType.EVENT)
                .event(EventType.MASS_SEND_JOB_FINISH).handler(massMsgHandler).end();

        // 默认
        newRouter.rule().async(false).handler(msgHandler).end();

        return newRouter;
    }
}
