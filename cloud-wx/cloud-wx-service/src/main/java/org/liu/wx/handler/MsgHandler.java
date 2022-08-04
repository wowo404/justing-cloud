/**
 * bs
 */
package org.liu.wx.handler;

import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.chanjar.weixin.common.api.WxConsts;
import me.chanjar.weixin.common.session.WxSessionManager;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.message.WxMpXmlMessage;
import me.chanjar.weixin.mp.bean.message.WxMpXmlOutMessage;
import me.chanjar.weixin.mp.bean.message.WxMpXmlOutNewsMessage;
import me.chanjar.weixin.mp.builder.outxml.*;
import org.liu.common.service.tenant.TenantContextHolder;
import org.liu.wx.feign.pojo.constants.WebSocketConstant;
import org.liu.wx.feign.pojo.enums.WxAutoReplyRepMateEnum;
import org.liu.wx.feign.pojo.enums.WxAutoReplyTypeEnum;
import org.liu.wx.feign.pojo.enums.WxMsgReadFlagEnum;
import org.liu.wx.feign.pojo.enums.WxMsgTypeEnum;
import org.liu.wx.feign.pojo.po.WxApp;
import org.liu.wx.feign.pojo.po.WxAutoReply;
import org.liu.wx.feign.pojo.po.WxMsg;
import org.liu.wx.feign.pojo.po.WxUser;
import org.liu.wx.service.WxAppService;
import org.liu.wx.service.WxAutoReplyService;
import org.liu.wx.service.WxMsgService;
import org.liu.wx.service.WxUserService;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static me.chanjar.weixin.common.api.WxConsts.XmlMsgType;

/**
 * @author JL
 */
@Slf4j
@Component
@AllArgsConstructor
public class MsgHandler extends AbstractHandler {

    private final WxAutoReplyService wxAutoReplyService;
    private final WxAppService wxAppService;
    private final WxUserService wxUserService;
    private final WxMsgService wxMsgService;
    private final SimpMessagingTemplate simpMessagingTemplate;

    @Override
    public WxMpXmlOutMessage handle(WxMpXmlMessage wxMessage,
                                    Map<String, Object> context, WxMpService wxMpService,
                                    WxSessionManager sessionManager) {
        //组装回复消息
        if (!wxMessage.getMsgType().equals(XmlMsgType.EVENT)) {
            WxMpXmlOutMessage rs;
            //可以选择将消息保存到本地
            WxApp wxApp = wxAppService.queryByWeixinSign(wxMessage.getToUser());
            TenantContextHolder.setTenantId(wxApp.getTenantId());//加入租户ID
            WxUser wxUser = wxUserService.queryByOpenId(wxMessage.getFromUser());
            if (WxConsts.KefuMsgType.TEXT.equals(wxMessage.getMsgType())) {//1、先处理是否有文本关键字回复
                //先全匹配
                List<WxAutoReply> listWxAutoReply = wxAutoReplyService.list(Wrappers
                        .<WxAutoReply>query().lambda()
                        .eq(WxAutoReply::getAppId, wxApp.getAppId())
                        .eq(WxAutoReply::getType, WxAutoReplyTypeEnum.KEYWORD.getCode())
                        .eq(WxAutoReply::getRepMate, WxAutoReplyRepMateEnum.FULL.getCode())
                        .eq(WxAutoReply::getReqKey, wxMessage.getContent()));
                if (listWxAutoReply != null && listWxAutoReply.size() > 0) {
                    rs = MsgHandler.getWxMpXmlOutMessage(wxMessage, listWxAutoReply, wxApp, wxUser, wxMsgService, simpMessagingTemplate);
                    if (rs != null) {
                        return rs;
                    }
                }
                //再半匹配
                listWxAutoReply = wxAutoReplyService.list(Wrappers
                        .<WxAutoReply>query().lambda()
                        .eq(WxAutoReply::getTenantId, wxApp.getTenantId())
                        .eq(WxAutoReply::getAppId, wxApp.getAppId())
                        .eq(WxAutoReply::getType, WxAutoReplyTypeEnum.KEYWORD.getCode())
                        .eq(WxAutoReply::getRepMate, WxAutoReplyRepMateEnum.HALF.getCode())
                        .like(WxAutoReply::getReqKey, wxMessage.getContent()));
                if (listWxAutoReply != null && listWxAutoReply.size() > 0) {
                    rs = MsgHandler.getWxMpXmlOutMessage(wxMessage, listWxAutoReply, wxApp, wxUser, wxMsgService, simpMessagingTemplate);
                    if (rs != null) {
                        return rs;
                    }
                }
            }
            //2、再处理消息回复
            List<WxAutoReply> listWxAutoReply = wxAutoReplyService.list(Wrappers
                    .<WxAutoReply>query().lambda()
                    .eq(WxAutoReply::getTenantId, wxApp.getTenantId())
                    .eq(WxAutoReply::getAppId, wxApp.getAppId())
                    .eq(WxAutoReply::getType, WxAutoReplyTypeEnum.MESSAGE.getCode())
                    .eq(WxAutoReply::getReqType, wxMessage.getMsgType()));
            rs = MsgHandler.getWxMpXmlOutMessage(wxMessage, listWxAutoReply, wxApp, wxUser, wxMsgService, simpMessagingTemplate);
            return rs;
        }
        return null;

    }

    /**
     * 组装回复消息，并记录消息
     *
     * @param wxMessage
     * @param listWxAutoReply
     * @return
     */
    public static WxMpXmlOutMessage getWxMpXmlOutMessage(WxMpXmlMessage wxMessage, List<WxAutoReply> listWxAutoReply, WxApp wxApp, WxUser wxUser,
                                                         WxMsgService wxMsgService, SimpMessagingTemplate simpMessagingTemplate) {
        WxMpXmlOutMessage wxMpXmlOutMessage = null;
        //记录接收消息
        WxMsg wxMsg = new WxMsg();
        wxMsg.setAppId(wxApp.getAppId());
        wxMsg.setWxUserId(wxUser.getId());
        wxMsg.setAppName(wxApp.getName());
        wxMsg.setAppLogo(wxApp.getLogo());
        wxMsg.setNickName(wxUser.getNickName());
        wxMsg.setHeadimgUrl(wxUser.getAvatarUrl());
        wxMsg.setType(WxMsgTypeEnum.USER_TO_MP.getCode());
        wxMsg.setRepEvent(wxMessage.getEvent());
        wxMsg.setRepType(wxMessage.getMsgType());
        wxMsg.setRepMediaId(wxMessage.getMediaId());
        if (WxConsts.XmlMsgType.TEXT.equals(wxMessage.getMsgType())) {
            wxMsg.setRepContent(wxMessage.getContent());
        }
        if (WxConsts.XmlMsgType.VOICE.equals(wxMessage.getMsgType())) {
            wxMsg.setRepName(wxMessage.getMediaId() + "." + wxMessage.getFormat());
            wxMsg.setRepContent(wxMessage.getRecognition());
        }
        if (WxConsts.XmlMsgType.IMAGE.equals(wxMessage.getMsgType())) {
            wxMsg.setRepUrl(wxMessage.getPicUrl());
        }
        if (WxConsts.XmlMsgType.LINK.equals(wxMessage.getMsgType())) {
            wxMsg.setRepName(wxMessage.getTitle());
            wxMsg.setRepDesc(wxMessage.getDescription());
            wxMsg.setRepUrl(wxMessage.getUrl());
        }
        if (WxConsts.MediaFileType.FILE.equals(wxMessage.getMsgType())) {
            wxMsg.setRepName(wxMessage.getTitle());
            wxMsg.setRepDesc(wxMessage.getDescription());
        }
        if (WxConsts.XmlMsgType.VIDEO.equals(wxMessage.getMsgType())) {
            wxMsg.setRepThumbMediaId(wxMessage.getThumbMediaId());
        }
        if (WxConsts.XmlMsgType.LOCATION.equals(wxMessage.getMsgType())) {
            wxMsg.setRepLocationX(wxMessage.getLocationX());
            wxMsg.setRepLocationY(wxMessage.getLocationY());
            wxMsg.setRepScale(wxMessage.getScale());
            wxMsg.setRepContent(wxMessage.getLabel());
        }
        wxMsg.setReadFlag(WxMsgReadFlagEnum.NO.getCode());
        wxMsgService.save(wxMsg);
        //推送websocket
        String destination = WebSocketConstant.USER_DESTINATION_PREFIX + WebSocketConstant.WX_MSG + wxMsg.getWxUserId();
        try {
            simpMessagingTemplate.convertAndSend(destination, JSONUtil.toJsonStr(wxMsg));
        } catch (Exception e) {
        }
        if (listWxAutoReply != null && listWxAutoReply.size() > 0) {
            WxAutoReply wxAutoReply = listWxAutoReply.get(0);
            //记录回复消息
            wxMsg = new WxMsg();
            wxMsg.setAppId(wxApp.getAppId());
            wxMsg.setWxUserId(wxUser.getId());
            wxMsg.setAppName(wxApp.getName());
            wxMsg.setAppLogo(wxApp.getLogo());
            wxMsg.setNickName(wxUser.getNickName());
            wxMsg.setHeadimgUrl(wxUser.getAvatarUrl());
            wxMsg.setType(WxMsgTypeEnum.USER_TO_MP.getCode());
            wxMsg.setRepType(wxAutoReply.getRepType());

            if (WxConsts.KefuMsgType.TEXT.equals(wxAutoReply.getRepType())) {//文本
                wxMsg.setRepContent(wxAutoReply.getRepContent());
                wxMpXmlOutMessage = new TextBuilder().fromUser(wxMessage.getToUser()).toUser(wxMessage.getFromUser()).content(wxAutoReply.getRepContent()).build();
            }
            if (WxConsts.KefuMsgType.IMAGE.equals(wxAutoReply.getRepType())) {//图片
                wxMsg.setRepName(wxAutoReply.getRepName());
                wxMsg.setRepUrl(wxAutoReply.getRepUrl());
                wxMsg.setRepMediaId(wxAutoReply.getRepMediaId());
                wxMpXmlOutMessage = new ImageBuilder().fromUser(wxMessage.getToUser()).toUser(wxMessage.getFromUser()).mediaId(wxAutoReply.getRepMediaId()).build();
            }
            if (WxConsts.KefuMsgType.VOICE.equals(wxAutoReply.getRepType())) {
                wxMsg.setRepName(wxAutoReply.getRepName());
                wxMsg.setRepUrl(wxAutoReply.getRepUrl());
                wxMsg.setRepMediaId(wxAutoReply.getRepMediaId());
                wxMpXmlOutMessage = new VoiceBuilder().fromUser(wxMessage.getToUser()).toUser(wxMessage.getFromUser()).mediaId(wxAutoReply.getRepMediaId()).build();
            }
            if (WxConsts.KefuMsgType.VIDEO.equals(wxAutoReply.getRepType())) {
                wxMsg.setRepName(wxAutoReply.getRepName());
                wxMsg.setRepDesc(wxAutoReply.getRepDesc());
                wxMsg.setRepUrl(wxAutoReply.getRepUrl());
                wxMsg.setRepMediaId(wxAutoReply.getRepMediaId());
                wxMpXmlOutMessage = new VideoBuilder().fromUser(wxMessage.getToUser()).toUser(wxMessage.getFromUser()).mediaId(wxAutoReply.getRepMediaId())
                        .title(wxAutoReply.getRepName()).description(wxAutoReply.getRepDesc()).build();
            }
            if (WxConsts.KefuMsgType.MUSIC.equals(wxAutoReply.getRepType())) {
                wxMsg.setRepName(wxAutoReply.getRepName());
                wxMsg.setRepDesc(wxAutoReply.getRepDesc());
                wxMsg.setRepUrl(wxAutoReply.getRepUrl());
                wxMsg.setRepHqUrl(wxAutoReply.getRepHqUrl());
                wxMsg.setRepThumbMediaId(wxAutoReply.getRepThumbMediaId());
                wxMsg.setRepThumbUrl(wxAutoReply.getRepThumbUrl());
                wxMpXmlOutMessage = new MusicBuilder().fromUser(wxMessage.getToUser()).toUser(wxMessage.getFromUser())
                        .thumbMediaId(wxAutoReply.getRepThumbMediaId())
                        .title(wxAutoReply.getRepName()).description(wxAutoReply.getRepDesc())
                        .musicUrl(wxAutoReply.getRepUrl()).hqMusicUrl(wxAutoReply.getRepHqUrl()).build();
            }
            if (WxConsts.KefuMsgType.NEWS.equals(wxAutoReply.getRepType())) {
                List<WxMpXmlOutNewsMessage.Item> list = new ArrayList<>();
                List<JSONObject> listJSONObject = JSONUtil.parseObj(wxAutoReply.getContent()).getJSONArray("articles").toList(JSONObject.class);
                WxMpXmlOutNewsMessage.Item t;
                for (JSONObject jSONObject : listJSONObject) {
                    t = new WxMpXmlOutNewsMessage.Item();
                    t.setTitle(jSONObject.getStr("title"));
                    t.setDescription(jSONObject.getStr("digest"));
                    t.setPicUrl(jSONObject.getStr("thumbUrl"));
                    t.setUrl(jSONObject.getStr("url"));
                    list.add(t);
                }
                wxMsg.setRepName(wxAutoReply.getRepName());
                wxMsg.setRepDesc(wxAutoReply.getRepDesc());
                wxMsg.setRepUrl(wxAutoReply.getRepUrl());
                wxMsg.setRepMediaId(wxAutoReply.getRepMediaId());
                wxMsg.setContent(wxAutoReply.getContent());
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
