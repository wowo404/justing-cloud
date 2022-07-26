package org.liu.order.feign.pojo.enums;

/**
 * 支付场景（0、在线支付；1、线下支付）
 */
public enum PaymentWayEnum {
    ONLINE(0, "在线支付"), OFFLINE(1, "线下支付");

    private Integer code;
    private String msg;

    PaymentWayEnum(Integer code, String msg) {
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
