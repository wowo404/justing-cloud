package org.liu.order.feign.pojo.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 支付状态（0、未支付；1、支付中；2、支付成功；3、支付失败）
 */
@Getter
@AllArgsConstructor
public enum PayStatusEnum {
    NOT_PAY(0, "未支付"), IN_PAYING(1, "支付中"), SUCCESS(2, "支付成功"), FAILURE(3, "支付失败");

    private final Integer code;
    private final String msg;
}
