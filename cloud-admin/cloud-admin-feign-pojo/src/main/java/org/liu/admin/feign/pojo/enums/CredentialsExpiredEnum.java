package org.liu.admin.feign.pojo.enums;

public enum CredentialsExpiredEnum {
    NO(0, "否"), YES(1, "是");

    private Integer code;
    private String msg;

    CredentialsExpiredEnum(Integer code, String msg) {
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
