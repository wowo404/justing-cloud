package org.liu.product.feign.pojo.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.justing.commons.exception.CodeEnum;

/**
 * product服务的错误码以20开头
 */
@Getter
@AllArgsConstructor
public enum BizCodeEnum implements CodeEnum {

    MISSING_HEADER_CLIENT("200000", "请求头client为必须的参数"),
    ;

    private final String code;
    private final String msg;
}
