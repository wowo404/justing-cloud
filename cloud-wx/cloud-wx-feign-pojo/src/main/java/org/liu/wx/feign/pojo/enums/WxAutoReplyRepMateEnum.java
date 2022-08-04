package org.liu.wx.feign.pojo.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 回复类型文本匹配类型（1、全匹配，2、半匹配）
 */
@Getter
@AllArgsConstructor
public enum WxAutoReplyRepMateEnum {
    FULL(1, "全匹配"), HALF(2, "半匹配");

    private final Integer code;
    private final String msg;
}
