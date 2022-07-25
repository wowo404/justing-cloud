package org.liu.common.core.enums;

public enum DeletedEnum {
    EXISTS(0, "存在"), DELETED(1, "删除");

    private Integer code;
    private String msg;

    DeletedEnum(Integer code, String msg) {
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
