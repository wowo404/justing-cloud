package org.liu.wx.feign.pojo.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 是否第三方平台应用（0：否；1：是）
 */
@Getter
@AllArgsConstructor
public enum IsComponentEnum {
    NO(0, "否"), YES(1, "是");

    private final Integer code;
    private final String msg;
}
