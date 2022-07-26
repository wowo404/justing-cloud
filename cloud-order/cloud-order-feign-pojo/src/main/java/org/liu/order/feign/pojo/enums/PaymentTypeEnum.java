package org.liu.order.feign.pojo.enums;

/**
 * 支付类型（0、微信支付；1、支付宝支付；2、云闪付）
 */
public enum PaymentTypeEnum {
    WX_PAY(0, "微信支付"), ALI_PAY(1, "支付宝支付"), UNION_PAY(2, "云闪付");

    private Integer code;
    private String msg;

    PaymentTypeEnum(Integer code, String msg) {
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
