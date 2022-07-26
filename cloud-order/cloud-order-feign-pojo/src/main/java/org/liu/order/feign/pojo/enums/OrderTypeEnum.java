package org.liu.order.feign.pojo.enums;

/**
 * 订单类型（0、普通订单；1、砍价订单）
 *
 * @Author lzs
 * @Date 2022/7/26 15:43
 **/
public enum OrderTypeEnum {
    NORMAL(0, "普通订单"), BARGAIN(1, "砍价订单");

    private Integer code;
    private String msg;

    OrderTypeEnum(Integer code, String msg) {
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
