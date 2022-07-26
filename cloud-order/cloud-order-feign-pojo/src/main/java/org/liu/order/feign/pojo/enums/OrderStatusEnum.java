package org.liu.order.feign.pojo.enums;

/**
 * 订单状态（0、待发货；1、待收货；2、确认收货/已完成；3、已关闭）
 */
public enum OrderStatusEnum {
    WAITING_DELIVERY(0, "待发货"), WAITING_RECEIVE(1, "待收货"), RECEIVED(2, "确认收货/已完成"), CLOSED(3, "已关闭");

    private Integer code;
    private String msg;

    OrderStatusEnum(Integer code, String msg) {
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
