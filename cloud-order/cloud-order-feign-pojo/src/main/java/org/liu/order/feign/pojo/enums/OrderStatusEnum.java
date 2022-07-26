package org.liu.order.feign.pojo.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 订单状态（0、待发货；1、待收货；2、确认收货/已完成；3、已关闭）
 */
@Getter
@AllArgsConstructor
public enum OrderStatusEnum {
    WAITING_DELIVERY(0, "待发货"), WAITING_RECEIVE(1, "待收货"), RECEIVED(2, "确认收货/已完成"), CLOSED(3, "已关闭");

    private final Integer code;
    private final String msg;
}
