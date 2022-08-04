package org.liu.wx.feign.pojo.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 类型（1、关注时回复；2、消息回复；3、关键词回复）
 */
@Getter
@AllArgsConstructor
public enum WxAutoReplyTypeEnum {
    SUBSCRIBE(1, "关注时回复"), MESSAGE(2, "消息回复"), KEYWORD(3, "关键词回复");

    private final Integer code;
    private final String msg;
}
