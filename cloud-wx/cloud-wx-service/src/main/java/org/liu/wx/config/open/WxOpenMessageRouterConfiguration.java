/**
 * bs
 */
package org.liu.wx.config.open;

import lombok.extern.slf4j.Slf4j;
import me.chanjar.weixin.common.api.WxConsts;
import me.chanjar.weixin.mp.constant.WxMpEventConstants;
import me.chanjar.weixin.open.api.WxOpenService;
import me.chanjar.weixin.open.api.impl.WxOpenMessageRouter;
import org.liu.wx.handler.*;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;

/**
 * 第三方平台Configuration
 *
 * @author JL
 */
@Slf4j
@Configuration
public class WxOpenMessageRouterConfiguration {
    private static WxOpenMessageRouter newRouter;
    private static WxOpenService wxOpenService;
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

    public WxOpenMessageRouterConfiguration(LogHandler logHandler,
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
                                            WxOpenService wxOpenService) {
        WxOpenMessageRouterConfiguration.logHandler = logHandler;
        WxOpenMessageRouterConfiguration.nullHandler = nullHandler;
        WxOpenMessageRouterConfiguration.kfSessionHandler = kfSessionHandler;
        WxOpenMessageRouterConfiguration.storeCheckNotifyHandler = storeCheckNotifyHandler;
        WxOpenMessageRouterConfiguration.locationHandler = locationHandler;
        WxOpenMessageRouterConfiguration.menuHandler = menuHandler;
        WxOpenMessageRouterConfiguration.msgHandler = msgHandler;
        WxOpenMessageRouterConfiguration.unSubscribeHandler = unSubscribeHandler;
        WxOpenMessageRouterConfiguration.subscribeHandler = subscribeHandler;
        WxOpenMessageRouterConfiguration.massMsgHandler = massMsgHandler;
        WxOpenMessageRouterConfiguration.userGetCardHandler = userGetCardHandler;
        WxOpenMessageRouterConfiguration.userDelCardHandler = userDelCardHandler;
        WxOpenMessageRouterConfiguration.userActivateCardHandler = userActivateCardHandler;
        WxOpenMessageRouterConfiguration.wxOpenService = wxOpenService;
    }

    @PostConstruct
    public void init() {
        newRouter = new WxOpenMessageRouter(wxOpenService);
        // 记录所有事件的日志 （异步执行）
        newRouter.rule().handler(logHandler).next();

        // 接收客服会话管理事件
        newRouter.rule().async(false).msgType(WxConsts.XmlMsgType.EVENT)
                .event(WxMpEventConstants.CustomerService.KF_CREATE_SESSION)
                .handler(kfSessionHandler).end();
        newRouter.rule().async(false).msgType(WxConsts.XmlMsgType.EVENT)
                .event(WxMpEventConstants.CustomerService.KF_CLOSE_SESSION)
                .handler(kfSessionHandler)
                .end();
        newRouter.rule().async(false).msgType(WxConsts.XmlMsgType.EVENT)
                .event(WxMpEventConstants.CustomerService.KF_SWITCH_SESSION)
                .handler(kfSessionHandler).end();

        // 门店审核事件
        newRouter.rule().async(false).msgType(WxConsts.XmlMsgType.EVENT)
                .event(WxMpEventConstants.POI_CHECK_NOTIFY)
                .handler(storeCheckNotifyHandler).end();

        // 自定义菜单事件
        newRouter.rule().async(false).msgType(WxConsts.XmlMsgType.EVENT)
                .event(WxConsts.MenuButtonType.CLICK).handler(menuHandler).end();

        // 点击菜单连接事件
        newRouter.rule().async(false).msgType(WxConsts.XmlMsgType.EVENT)
                .event(WxConsts.MenuButtonType.VIEW).handler(menuHandler).end();

        // 扫码事件
        newRouter.rule().async(false).msgType(WxConsts.XmlMsgType.EVENT)
                .event(WxConsts.EventType.SCANCODE_WAITMSG).handler(menuHandler).end();

        // 关注事件
        newRouter.rule().async(false).msgType(WxConsts.XmlMsgType.EVENT)
                .event(WxConsts.EventType.SUBSCRIBE).handler(subscribeHandler)
                .end();

        // 取消关注事件
        newRouter.rule().async(false).msgType(WxConsts.XmlMsgType.EVENT)
                .event(WxConsts.EventType.UNSUBSCRIBE)
                .handler(unSubscribeHandler).end();

        // 上报地理位置事件
        newRouter.rule().async(false).msgType(WxConsts.XmlMsgType.EVENT)
                .event(WxConsts.EventType.LOCATION).handler(locationHandler)
                .end();

        // 卡券领取事件
        newRouter.rule().async(false).msgType(WxConsts.XmlMsgType.EVENT)
                .event(WxConsts.EventType.CARD_USER_GET_CARD).handler(userGetCardHandler).end();

        // 卡券删除事件
        newRouter.rule().async(false).msgType(WxConsts.XmlMsgType.EVENT)
                .event(WxConsts.EventType.CARD_USER_DEL_CARD).handler(userDelCardHandler).end();

        // 卡券激活事件
        newRouter.rule().async(false).msgType(WxConsts.XmlMsgType.EVENT)
                .event(WxConsts.EventType.CARD_SUBMIT_MEMBERCARD_USER_INFO).handler(userActivateCardHandler).end();

        // 群发回调事件
        newRouter.rule().async(false).msgType(WxConsts.XmlMsgType.EVENT)
                .event(WxConsts.EventType.MASS_SEND_JOB_FINISH).handler(massMsgHandler).end();

        // 默认
        newRouter.rule().async(false).handler(msgHandler).end();
    }

    public static WxOpenMessageRouter getWxOpenMessageRouter() {
        return newRouter;
    }
}
