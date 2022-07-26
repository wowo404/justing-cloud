package org.liu.order.feign.pojo.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 订单类型（0、普通订单；1、砍价订单）
 *
 * @Author lzs
 * @Date 2022/7/26 15:43
 **/
@Getter
@AllArgsConstructor
public enum OrderTypeEnum {
    NORMAL(0, "普通订单"), BARGAIN(1, "砍价订单");

    private final Integer code;
    private final String msg;
}
