package org.liu.admin.feign.pojo.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum OperatorStatusEnum {
    NORMAL(0, "正常"), LOCKED(1, "冻结");

    private final Integer code;
    private final String msg;
}
