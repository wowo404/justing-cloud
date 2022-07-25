package org.liu.common.core.enums;

public enum OperatorStatusEnum {
    NORMAL(0, "正常"), LOCKED(1, "冻结");

    private Integer code;
    private String msg;

    OperatorStatusEnum(Integer code, String msg) {
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
