package org.liu.order.feign.pojo.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 支付场景（0、在线支付；1、线下支付）
 */
@Getter
@AllArgsConstructor
public enum PaymentWayEnum {
    ONLINE(0, "在线支付"), OFFLINE(1, "线下支付");

    private final Integer code;
    private final String msg;
}
