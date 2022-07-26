package org.liu.order.feign.pojo.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.justing.commons.exception.CodeEnum;

/**
 * order服务的错误码以19开头
 */
@Getter
@AllArgsConstructor
public enum BizCodeEnum implements CodeEnum {

    MISSING_PAYMENT_TYPE("190000", "在线支付必须选择支付方式"),
    WRONG_PAYMENT_PRICE("190001", "支付金额计算有误"),
    ;

    private final String code;
    private final String msg;
}
