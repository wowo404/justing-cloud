/**
 * bs
 */
package org.liu.wx.handler;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.chanjar.weixin.common.api.WxConsts;
import me.chanjar.weixin.common.session.WxSessionManager;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.message.WxMpXmlMessage;
import me.chanjar.weixin.mp.bean.message.WxMpXmlOutMessage;
import org.liu.common.service.tenant.TenantContextHolder;
import org.liu.wx.feign.pojo.po.WxApp;
import org.liu.wx.feign.pojo.po.WxUser;
import org.liu.wx.service.WxAppService;
import org.liu.wx.service.WxUserService;
import org.springframework.stereotype.Component;

import java.util.Map;


/**
 * @author JL
 */
@Slf4j
@Component
@AllArgsConstructor
public class LocationHandler extends AbstractHandler {

    private final WxUserService wxUserService;
    private final WxAppService wxAppService;

    @Override
    public WxMpXmlOutMessage handle(WxMpXmlMessage wxMessage,
                                    Map<String, Object> context, WxMpService wxMpService,
                                    WxSessionManager sessionManager) {
        if (wxMessage.getEvent().equals(WxConsts.EventType.LOCATION)) {
            try {
                WxApp wxApp = wxAppService.queryByWeixinSign(wxMessage.getToUser());
                TenantContextHolder.setTenantId(wxApp.getTenantId());//加入租户ID
                WxUser wxUser = wxUserService.queryByOpenId(wxMessage.getFromUser());
                wxUser.setLatitude(wxMessage.getLatitude());
                wxUser.setLongitude(wxMessage.getLongitude());
                wxUser.setPrecision(wxMessage.getPrecision());
                wxUserService.updateById(wxUser);
                return null;
            } catch (Exception e) {
                e.printStackTrace();
                log.error("位置消息接收处理失败", e);
                return null;
            }
        }
        return null;
    }

}
