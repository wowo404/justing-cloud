package org.liu.wx.feign.pojo.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 已读标记（0：否；1：是）
 */
@Getter
@AllArgsConstructor
public enum WxMsgReadFlagEnum {
    NO(0, "否"), YES(1, "是");

    private final Integer code;
    private final String msg;
}
