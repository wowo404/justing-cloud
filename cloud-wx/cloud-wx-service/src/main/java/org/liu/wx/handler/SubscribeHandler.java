/**
 * bs
 */
package org.liu.wx.handler;

import cn.hutool.json.JSONUtil;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.chanjar.weixin.common.session.WxSessionManager;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.message.WxMpXmlMessage;
import me.chanjar.weixin.mp.bean.message.WxMpXmlOutMessage;
import me.chanjar.weixin.mp.bean.result.WxMpUser;
import org.liu.common.service.tenant.TenantContextHolder;
import org.liu.wx.feign.pojo.enums.WxAutoReplyTypeEnum;
import org.liu.wx.feign.pojo.enums.WxUserSubscribeEnum;
import org.liu.wx.feign.pojo.po.WxApp;
import org.liu.wx.feign.pojo.po.WxAutoReply;
import org.liu.wx.feign.pojo.po.WxUser;
import org.liu.wx.service.WxAppService;
import org.liu.wx.service.WxAutoReplyService;
import org.liu.wx.service.WxMsgService;
import org.liu.wx.service.WxUserService;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author JL
 * 用户关注
 */
@Slf4j
@Component
@AllArgsConstructor
public class SubscribeHandler extends AbstractHandler {

    private final WxAutoReplyService wxAutoReplyService;
    private final WxAppService wxAppService;
    private final WxUserService wxUserService;
    private final WxMsgService wxMsgService;
    private final SimpMessagingTemplate simpMessagingTemplate;

    @Override
    public WxMpXmlOutMessage handle(WxMpXmlMessage wxMessage,
                                    Map<String, Object> context, WxMpService wxMpService,
                                    WxSessionManager sessionManager) {
        log.info("新关注用户 OPENID: " + wxMessage.getFromUser());
        // 获取微信用户基本信息
        try {
            WxMpUser userWxInfo = wxMpService.getUserService()
                    .userInfo(wxMessage.getFromUser(), null);
            if (userWxInfo != null) {
                // 添加关注用户到本地数据库
                WxApp wxApp = wxAppService.queryByWeixinSign(wxMessage.getToUser());
                TenantContextHolder.setTenantId(wxApp.getTenantId());//加入租户ID
				WxUser wxUser = wxUserService.queryByOpenId(userWxInfo.getOpenId());
				if (wxUser == null) {//第一次关注
					wxUser = new WxUser();
					wxUser.setSubscribeNum(1);
					this.setWxUserValue(wxApp, wxUser, userWxInfo);
					wxUserService.save(wxUser);
				} else {//曾经关注过
					wxUser.setSubscribeNum(wxUser.getSubscribeNum() + 1);
					this.setWxUserValue(wxApp, wxUser, userWxInfo);
					wxUserService.updateById(wxUser);
				}
				//发送关注消息
				List<WxAutoReply> listWxAutoReply = wxAutoReplyService.list(Wrappers.<WxAutoReply>query()
						.lambda().eq(WxAutoReply::getAppId, wxApp.getAppId()).eq(WxAutoReply::getType, WxAutoReplyTypeEnum.SUBSCRIBE.getCode()));
				return MsgHandler.getWxMpXmlOutMessage(wxMessage, listWxAutoReply, wxApp, wxUser, wxMsgService, simpMessagingTemplate);
			}
        } catch (Exception e) {
            log.error("用户关注出错：" + e.getMessage());
        }
        return null;
    }

    public void setWxUserValue(WxApp wxApp, WxUser wxUser, WxMpUser userWxInfo) {
        wxUser.setAppId(wxApp.getAppId());
        wxUser.setSubscribe(WxUserSubscribeEnum.YES.getCode());
        wxUser.setSubscribeScene(userWxInfo.getSubscribeScene());
        wxUser.setSubscribeTime(new Date(userWxInfo.getSubscribeTime() * 1000));
        wxUser.setOpenId(userWxInfo.getOpenId());
        wxUser.setNickName(userWxInfo.getNickname());
        wxUser.setLanguage(userWxInfo.getLanguage());
        wxUser.setRemark(userWxInfo.getRemark());
        wxUser.setAvatarUrl(userWxInfo.getHeadImgUrl());
        wxUser.setUnionId(userWxInfo.getUnionId());
        wxUser.setGroupId(JSONUtil.toJsonStr(userWxInfo.getGroupId()));
        String tagidList = Stream.of(userWxInfo.getTagIds()).map(String::valueOf).collect(Collectors.joining(","));
        wxUser.setTagidList(tagidList);
        wxUser.setQrSceneStr(userWxInfo.getQrSceneStr());
    }
}
