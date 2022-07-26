package org.liu.wx.feign.pojo.exception;

import org.justing.commons.exception.CodeEnum;

/**
 * wx服务的错误码以24开头
 */
public enum BizCodeEnum implements CodeEnum {

    MISSING_HEADER_CLIENT("240000", "请求头client为必须的参数"),
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
