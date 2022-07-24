package org.liu.auth.exception;

import org.justing.commons.exception.CodeEnum;

public enum BizCodeEnum implements CodeEnum {

    MISSING_HEADER_CLIENT("100000", "请求头client为必须的参数");

    private String code;
    private String msg;

    BizCodeEnum(String code, String msg){
        this.code = code;
        this.msg = msg;
    }

    @Override
    public String getCode() {
        return code;
    }

    @Override
    public String getMsg() {
        return msg;
    }
}
