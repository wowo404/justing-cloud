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

    MISSING_MINIAPP_CONFIG("240000", "系统内无此小程序：%s"),
    WX_ERROR("240001", "微信接口异常：%s"),
    WX_ERROR_GET_SESSION_INFO("240002", "微信登录失败：jsCode换sessionInfo失败"),
    ;

    private final String code;
    private final String msg;
}
