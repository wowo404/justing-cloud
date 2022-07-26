package org.liu.product.feign.pojo.exception;

import org.justing.commons.exception.CodeEnum;

/**
 * product服务的错误码以20开头
 */
public enum BizCodeEnum implements CodeEnum {

    MISSING_HEADER_CLIENT("200000", "请求头client为必须的参数"),
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
