/**
 * bs
 */
package org.liu.wx.handler;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.chanjar.weixin.common.api.WxConsts;
import me.chanjar.weixin.common.session.WxSessionManager;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.message.WxMpXmlMessage;
import me.chanjar.weixin.mp.bean.message.WxMpXmlOutMessage;
import org.liu.common.service.tenant.TenantContextHolder;
import org.liu.wx.feign.pojo.enums.WxMassMsgStatusEnum;
import org.liu.wx.feign.pojo.po.WxApp;
import org.liu.wx.feign.pojo.po.WxMassMsg;
import org.liu.wx.service.WxAppService;
import org.liu.wx.service.WxMassMsgService;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * 群发结果回调
 *
 * @author JL
 */
@Slf4j
@Component
@AllArgsConstructor
public class MassMsgHandler extends AbstractHandler {

    private final WxAppService wxAppService;
    private final WxMassMsgService wxMassMsgService;

    @Override
    public WxMpXmlOutMessage handle(WxMpXmlMessage wxMessage, Map<String, Object> context, WxMpService wxMpService,
                                    WxSessionManager sessionManager) {
        // 组装回复消息
        WxApp wxApp = wxAppService.queryByWeixinSign(wxMessage.getToUser());
        TenantContextHolder.setTenantId(wxApp.getTenantId());//加入租户ID
        String msgId = String.valueOf(wxMessage.getMsgId());
        WxMassMsg wxMassMsg = wxMassMsgService.getOne(Wrappers.<WxMassMsg>query().lambda()
                .eq(WxMassMsg::getAppId, wxApp.getAppId())
                .eq(WxMassMsg::getMsgId, msgId));
        String errCode = wxMessage.getStatus();
        wxMassMsg.setMsgStatus(WxConsts.MassMsgStatus.SEND_SUCCESS.equals(errCode) ? WxMassMsgStatusEnum.SEND_SUCCESS.name() : WxMassMsgStatusEnum.SEND_FAIL.name());
        wxMassMsg.setErrorCode(errCode);
        wxMassMsg.setErrorMsg(WxConsts.MassMsgStatus.STATUS_DESC.get(errCode));
        wxMassMsg.setTotalCount(wxMessage.getTotalCount());
        wxMassMsg.setSentCount(wxMessage.getSentCount());
        wxMassMsg.setErrorCount(wxMessage.getErrorCount());
        wxMassMsg.setFilterCount(wxMessage.getFilterCount());
        wxMassMsgService.updateById(wxMassMsg);
        return null;
    }

}
