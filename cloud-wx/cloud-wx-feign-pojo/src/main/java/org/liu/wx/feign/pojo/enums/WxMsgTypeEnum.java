package org.liu.wx.feign.pojo.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 消息分类（1、用户发给公众号；2、公众号发给用户；）
 */
@Getter
@AllArgsConstructor
public enum WxMsgTypeEnum {
    USER_TO_MP(1, "用户发给公众号"), MP_TO_USER(2, "公众号发给用户");

    private final Integer code;
    private final String msg;
}
