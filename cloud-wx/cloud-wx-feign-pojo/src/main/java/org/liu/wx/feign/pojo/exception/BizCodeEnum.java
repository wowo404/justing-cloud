package org.liu.wx.feign.pojo.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.justing.commons.exception.CodeEnum;

/**
 * wx服务的错误码以24开头
 */
@Getter
@AllArgsConstructor
public enum BizCodeEnum implements CodeEnum {

    MISSING_MINIAPP_CONFIG("240000", "系统内无此微信小程序：%s"),
    MISSING_OFFIACCOUNT_CONFIG("240001", "系统内无此微信公众号：%s"),
    MISSING_OPEN_CONFIG("240002", "系统内无此微信开放平台：%s"),
    MISSING_PAY_CONFIG("240003", "系统内无此微信支付号：%s"),
    WX_ERROR("240004", "微信接口异常：%s"),
    WX_ERROR_GET_SESSION_INFO("240005", "微信登录失败：jsCode换sessionInfo失败"),
    ;

    private final String code;
    private final String msg;
}
