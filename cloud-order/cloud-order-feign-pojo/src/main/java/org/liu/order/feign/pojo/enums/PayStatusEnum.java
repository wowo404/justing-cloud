package org.liu.order.feign.pojo.enums;

/**
 * 支付状态（0、未支付；1、支付中；2、支付成功；3、支付失败）
 */
public enum PayStatusEnum {
    NOT_PAY(0, "未支付"), IN_PAYING(1, "支付中"), SUCCESS(2, "支付成功"), FAILURE(3, "支付失败");

    private Integer code;
    private String msg;

    PayStatusEnum(Integer code, String msg) {
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
