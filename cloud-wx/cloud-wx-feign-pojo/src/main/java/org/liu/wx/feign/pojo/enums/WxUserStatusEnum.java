package org.liu.wx.feign.pojo.enums;

/**
 * 状态（0-正常，1-冻结）
 */
public enum WxUserStatusEnum {
    NORMAL(0, "正常"), LOCKED(1, "冻结");

    private Integer code;
    private String msg;

    WxUserStatusEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public Integer getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}
