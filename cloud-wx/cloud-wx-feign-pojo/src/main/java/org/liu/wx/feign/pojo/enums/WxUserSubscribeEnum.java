package org.liu.wx.feign.pojo.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 是否订阅（0：否；1：是；2：网页授权用户）
 */
@Getter
@AllArgsConstructor
public enum WxUserSubscribeEnum {
    NO(0, "否"), YES(1, "是"), WEB_PAGE_AUTHORIZE(2, "网页授权用户");

    private final Integer code;
    private final String msg;
}
