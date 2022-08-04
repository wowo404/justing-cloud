/**
 * bs
 */
package org.liu.wx.handler;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.chanjar.weixin.common.session.WxSessionManager;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.message.WxMpXmlMessage;
import me.chanjar.weixin.mp.bean.message.WxMpXmlOutMessage;
import org.liu.common.service.tenant.TenantContextHolder;
import org.liu.wx.feign.pojo.enums.WxUserSubscribeEnum;
import org.liu.wx.feign.pojo.po.WxApp;
import org.liu.wx.feign.pojo.po.WxUser;
import org.liu.wx.service.WxAppService;
import org.liu.wx.service.WxMsgService;
import org.liu.wx.service.WxUserService;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.Map;

/**
 * 用户取消关注
 * @author JL
 */
@Slf4j
@Component
@AllArgsConstructor
public class UnSubscribeHandler extends AbstractHandler {

    private final WxAppService wxAppService;
    private final WxUserService wxUserService;
    private final WxMsgService wxMsgService;
    private final SimpMessagingTemplate simpMessagingTemplate;

    @Override
    public WxMpXmlOutMessage handle(WxMpXmlMessage wxMessage,
                                    Map<String, Object> context, WxMpService wxMpService,
                                    WxSessionManager sessionManager) {
        String openId = wxMessage.getFromUser();
        log.info("取消关注用户 OPENID: " + openId);
        // 更新本地数据库为取消关注状态
        WxApp wxApp = wxAppService.queryByWeixinSign(wxMessage.getToUser());
        TenantContextHolder.setTenantId(wxApp.getTenantId());//加入租户ID
        WxUser wxUser = wxUserService.queryByOpenId(openId);
        if (wxUser != null) {
            wxUser.setSubscribe(WxUserSubscribeEnum.NO.getCode());
            wxUser.setCancelSubscribeTime(new Date());
            wxUserService.updateById(wxUser);
            //消息记录
            MsgHandler.getWxMpXmlOutMessage(wxMessage, null, wxApp, wxUser, wxMsgService, simpMessagingTemplate);
        }
        return null;
    }

}
