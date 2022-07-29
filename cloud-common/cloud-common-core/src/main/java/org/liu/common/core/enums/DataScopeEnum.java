package org.liu.common.core.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 数据范围（0-全部数据权限，1-本部门及以下数据权限，2-本部门数据权限，3-仅本人数据权限，4-自定义数据权限）
 */
@Getter
@AllArgsConstructor
public enum DataScopeEnum {
    ALL(0, "全部数据权限"),
    DEPT_AND_CHILD(1, "本部门及以下数据权限"),
    DEPT(2, "本部门数据权限"),
    SELF(3, "仅本人数据权限"),
    CUSTOM(4, "自定义数据权限");

    private final Integer code;
    private final String msg;

    public static DataScopeEnum parseByCode(Integer code) {
        for (DataScopeEnum value : DataScopeEnum.values()) {
            if (value.code.equals(code)) {
                return value;
            }
        }
        throw new EnumConstantNotPresentException(DataScopeEnum.class, code + "");
    }
}
