package org.liu.product.feign.pojo.enums;

/**
 * 是否上架，0下架，1上架
 */
public enum SaleableEnum {
    NOT_SALE(0, "下架"), ON_SALE(1, "上架");

    private Integer code;
    private String msg;

    SaleableEnum(Integer code, String msg) {
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
