package org.liu.order.feign.pojo.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 评价状态（0、未评；1、已评；2、已追评）
 */
@Getter
@AllArgsConstructor
public enum AppraiseStatusEnum {
    NONE(0, "未评"), APPRAISED(1, "已评"), APPENDED(2, "已追评");

    private final Integer code;
    private final String msg;
}
