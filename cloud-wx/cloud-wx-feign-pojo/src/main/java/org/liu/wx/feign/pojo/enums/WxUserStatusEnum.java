package org.liu.wx.feign.pojo.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 状态（0-正常，1-冻结）
 */
@Getter
@AllArgsConstructor
public enum WxUserStatusEnum {
    NORMAL(0, "正常"), LOCKED(1, "冻结");

    private final Integer code;
    private final String msg;
}
