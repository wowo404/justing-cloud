package org.liu.order.feign.pojo.exception;

import org.justing.commons.exception.CodeEnum;

/**
 * order服务的错误码以19开头
 */
public enum BizCodeEnum implements CodeEnum {

    MISSING_PAYMENT_TYPE("190000", "在线支付必须选择支付方式"),
    WRONG_PAYMENT_PRICE("190001", "支付金额计算有误"),
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
