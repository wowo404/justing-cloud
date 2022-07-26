package org.liu.order.feign.pojo.enums;

/**
 * 评价状态（0、未评；1、已评；2、已追评）
 */
public enum AppraiseStatusEnum {
    NONE(0, "未评"), APPRAISED(1, "已评"), APPENDED(2, "已追评");

    private Integer code;
    private String msg;

    AppraiseStatusEnum(Integer code, String msg) {
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
