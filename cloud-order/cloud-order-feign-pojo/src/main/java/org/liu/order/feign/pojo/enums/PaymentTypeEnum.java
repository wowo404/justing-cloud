package org.liu.order.feign.pojo.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 支付类型（0、微信支付；1、支付宝支付；2、云闪付）
 */
@Getter
@AllArgsConstructor
public enum PaymentTypeEnum {
    WX_PAY(0, "微信支付"), ALI_PAY(1, "支付宝支付"), UNION_PAY(2, "云闪付");

    private final Integer code;
    private final String msg;
}
