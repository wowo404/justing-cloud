/**
 * bs
 */
package org.liu.wx.handler;

import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.chanjar.weixin.common.api.WxConsts;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.common.session.WxSessionManager;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.message.WxMpXmlMessage;
import me.chanjar.weixin.mp.bean.message.WxMpXmlOutMessage;
import me.chanjar.weixin.mp.bean.message.WxMpXmlOutNewsMessage;
import me.chanjar.weixin.mp.bean.result.WxMpUser;
import me.chanjar.weixin.mp.builder.outxml.*;
import org.liu.common.service.tenant.TenantContextHolder;
import org.liu.wx.feign.pojo.constants.WebSocketConstant;
import org.liu.wx.feign.pojo.enums.WxMsgReadFlagEnum;
import org.liu.wx.feign.pojo.enums.WxMsgTypeEnum;
import org.liu.wx.feign.pojo.po.WxApp;
import org.liu.wx.feign.pojo.po.WxMpMenu;
import org.liu.wx.feign.pojo.po.WxMsg;
import org.liu.wx.feign.pojo.po.WxUser;
import org.liu.wx.service.WxAppService;
import org.liu.wx.service.WxMpMenuService;
import org.liu.wx.service.WxMsgService;
import org.liu.wx.service.WxUserService;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 自定义菜单处理
 *
 * @author JL
 */
@Slf4j
@Component
@AllArgsConstructor
public class MenuHandler extends AbstractHandler {

    private final WxMpMenuService wxMenuService;
    private final WxUserService wxUserService;
    private final WxAppService wxAppService;
    private final WxMsgService wxMsgService;
    private final SimpMessagingTemplate simpMessagingTemplate;

    @Override
    public WxMpXmlOutMessage handle(WxMpXmlMessage wxMessage,
                                    Map<String, Object> context, WxMpService weixinService,
                                    WxSessionManager sessionManager) throws WxErrorException {
        //消息记录
        WxApp wxApp = wxAppService.queryByWeixinSign(wxMessage.getToUser());
        TenantContextHolder.setTenantId(wxApp.getTenantId());//加入租户ID
        WxMpMenu wxMenu = null;
        if (WxConsts.EventType.CLICK.equals(wxMessage.getEvent())
                || WxConsts.EventType.SCANCODE_WAITMSG.equals(wxMessage.getEvent())) {
            wxMenu = wxMenuService.getById(wxMessage.getEventKey());
            if (wxMenu == null) {//菜单过期
                return new TextBuilder().fromUser(wxMessage.getToUser()).toUser(wxMessage.getFromUser()).content("非常抱歉，该菜单已删除！").build();
            }
        } else {
            wxMenu = new WxMpMenu();
        }
        WxUser wxUser = wxUserService.queryByOpenId(wxMessage.getFromUser());
        if (wxUser == null) {//库中无此用户
            WxMpUser userWxInfo = weixinService.getUserService()
                    .userInfo(wxMessage.getFromUser(), null);
            wxUser = new WxUser();
            wxUser.setSubscribeNum(1);
            SubscribeHandler.setWxUserValue(wxApp, wxUser, userWxInfo);
//			wxUser.setTenantId(wxApp.getTenantId());
            wxUserService.save(wxUser);
        }
        //组装菜单回复消息
        return getWxMpXmlOutMessage(wxMessage, wxMenu, wxApp, wxUser);
    }

    /**
     * 组装菜单回复消息
     *
     * @param wxMessage
     * @param wxMenu
     * @return
     */
    public WxMpXmlOutMessage getWxMpXmlOutMessage(WxMpXmlMessage wxMessage, WxMpMenu wxMenu, WxApp wxApp, WxUser wxUser) {
        WxMpXmlOutMessage wxMpXmlOutMessage = null;
        //记录接收消息
        WxMsg wxMsg = new WxMsg();
//		wxMsg.setTenantId(wxApp.getTenantId());
        wxMsg.setAppId(wxApp.getAppId());
        wxMsg.setWxUserId(wxUser.getId());
        wxMsg.setAppName(wxApp.getName());
        wxMsg.setAppLogo(wxApp.getLogo());
        wxMsg.setNickName(wxUser.getNickName());
        wxMsg.setHeadimgUrl(wxUser.getAvatarUrl());
        wxMsg.setType(WxMsgTypeEnum.USER_TO_MP.getCode());
        wxMsg.setRepEvent(wxMessage.getEvent());
        wxMsg.setRepType(wxMessage.getMsgType());
        wxMsg.setRepName(wxMenu.getName());
        if (WxConsts.EventType.VIEW.equals(wxMessage.getEvent())) {
            wxMsg.setRepUrl(wxMessage.getEventKey());
        }
        if (WxConsts.EventType.SCANCODE_WAITMSG.equals(wxMessage.getEvent())) {
            wxMsg.setRepContent(wxMessage.getScanCodeInfo().getScanResult());
        }
        wxMsg.setReadFlag(WxMsgReadFlagEnum.NO.getCode());
        wxMsgService.save(wxMsg);
        //推送websocket
        String destination = WebSocketConstant.USER_DESTINATION_PREFIX + WebSocketConstant.WX_MSG + wxMsg.getWxUserId();
        try {
            simpMessagingTemplate.convertAndSend(destination, JSONUtil.toJsonStr(wxMsg));
        } catch (Exception e) {
        }
        if (WxConsts.MenuButtonType.CLICK.equals(wxMenu.getType())
                || WxConsts.MenuButtonType.SCANCODE_WAITMSG.equals(wxMenu.getType())) {
            //记录回复消息
            wxMsg = new WxMsg();
//			wxMsg.setTenantId(wxApp.getTenantId());
            wxMsg.setAppId(wxApp.getAppId());
            wxMsg.setWxUserId(wxUser.getId());
            wxMsg.setAppName(wxApp.getName());
            wxMsg.setAppLogo(wxApp.getLogo());
            wxMsg.setNickName(wxUser.getNickName());
            wxMsg.setHeadimgUrl(wxUser.getAvatarUrl());
            wxMsg.setType(WxMsgTypeEnum.MP_TO_USER.getCode());
            wxMsg.setRepType(wxMenu.getRepType());
            if (WxConsts.KefuMsgType.TEXT.equals(wxMenu.getRepType())) {
                wxMsg.setRepContent(wxMenu.getRepContent());
                wxMpXmlOutMessage = new TextBuilder().fromUser(wxMessage.getToUser()).toUser(wxMessage.getFromUser()).content(wxMenu.getRepContent()).build();
            }
            if (WxConsts.KefuMsgType.IMAGE.equals(wxMenu.getRepType())) {
                wxMsg.setRepName(wxMenu.getRepName());
                wxMsg.setRepUrl(wxMenu.getRepUrl());
                wxMsg.setRepMediaId(wxMenu.getRepMediaId());
                wxMpXmlOutMessage = new ImageBuilder().fromUser(wxMessage.getToUser()).toUser(wxMessage.getFromUser()).mediaId(wxMenu.getRepMediaId()).build();
            }
            if (WxConsts.KefuMsgType.VOICE.equals(wxMenu.getRepType())) {
                wxMsg.setRepName(wxMenu.getRepName());
                wxMsg.setRepUrl(wxMenu.getRepUrl());
                wxMsg.setRepMediaId(wxMenu.getRepMediaId());
                wxMpXmlOutMessage = new VoiceBuilder().fromUser(wxMessage.getToUser()).toUser(wxMessage.getFromUser()).mediaId(wxMenu.getRepMediaId()).build();
            }
            if (WxConsts.KefuMsgType.VIDEO.equals(wxMenu.getRepType())) {
                wxMsg.setRepName(wxMenu.getRepName());
                wxMsg.setRepDesc(wxMenu.getRepDesc());
                wxMsg.setRepUrl(wxMenu.getRepUrl());
                wxMsg.setRepMediaId(wxMenu.getRepMediaId());
                wxMpXmlOutMessage = new VideoBuilder().fromUser(wxMessage.getToUser()).toUser(wxMessage.getFromUser()).mediaId(wxMenu.getRepMediaId())
                        .title(wxMenu.getRepName()).description(wxMenu.getRepDesc()).build();
            }
            if (WxConsts.KefuMsgType.MUSIC.equals(wxMenu.getRepType())) {
                wxMsg.setRepName(wxMenu.getRepName());
                wxMsg.setRepDesc(wxMenu.getRepDesc());
                wxMsg.setRepUrl(wxMenu.getRepUrl());
                wxMsg.setRepHqUrl(wxMenu.getRepHqUrl());
                wxMsg.setRepThumbMediaId(wxMenu.getRepThumbMediaId());
                wxMsg.setRepThumbUrl(wxMenu.getRepThumbUrl());
                wxMpXmlOutMessage = new MusicBuilder().fromUser(wxMessage.getToUser()).toUser(wxMessage.getFromUser())
                        .thumbMediaId(wxMenu.getRepThumbMediaId())
                        .title(wxMenu.getRepName()).description(wxMenu.getRepDesc())
                        .musicUrl(wxMenu.getRepUrl()).hqMusicUrl(wxMenu.getRepHqUrl()).build();
            }
            if (WxConsts.KefuMsgType.NEWS.equals(wxMenu.getRepType())) {
                List<WxMpXmlOutNewsMessage.Item> list = new ArrayList<>();
                List<JSONObject> listJSONObject = JSONUtil.parseObj(wxMenu.getContent()).getJSONArray("articles").toList(JSONObject.class);
                WxMpXmlOutNewsMessage.Item t;
                for (JSONObject jSONObject : listJSONObject) {
                    t = new WxMpXmlOutNewsMessage.Item();
                    t.setTitle(jSONObject.getStr("title"));
                    t.setDescription(jSONObject.getStr("digest"));
                    t.setPicUrl(jSONObject.getStr("thumbUrl"));
                    t.setUrl(jSONObject.getStr("url"));
                    list.add(t);
                }
                wxMsg.setRepName(wxMenu.getRepName());
                wxMsg.setRepDesc(wxMenu.getRepDesc());
                wxMsg.setRepUrl(wxMenu.getRepUrl());
                wxMsg.setRepMediaId(wxMenu.getRepMediaId());
                wxMsg.setContent(wxMenu.getContent());
                wxMpXmlOutMessage = new NewsBuilder().fromUser(wxMessage.getToUser()).toUser(wxMessage.getFromUser()).articles(list).build();
            }
            wxMsgService.save(wxMsg);
            try {
                simpMessagingTemplate.convertAndSend(destination, JSONUtil.toJsonStr(wxMsg));
            } catch (Exception e) {
            }
        }
        return wxMpXmlOutMessage;
    }
}
