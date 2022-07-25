package org.liu.auth.exception;

import org.justing.commons.exception.CodeEnum;

/**
 * auth服务的错误码以12开头
 */
public enum BizCodeEnum implements CodeEnum {

    MISSING_HEADER_CLIENT("120000", "请求头client为必须的参数"),
    ERROR_GET_OPERATOR_DETAIL("120001", "获取操作员详情错误"),
    ;

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
