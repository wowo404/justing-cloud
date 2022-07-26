package org.liu.auth.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.justing.commons.exception.CodeEnum;

/**
 * auth服务的错误码以12开头
 */
@Getter
@AllArgsConstructor
public enum BizCodeEnum implements CodeEnum {

    MISSING_HEADER_CLIENT("120000", "请求头client为必须的参数"),
    ERROR_GET_OPERATOR_DETAIL("120001", "获取操作员详情错误"),
    ;

    private final String code;
    private final String msg;
}
