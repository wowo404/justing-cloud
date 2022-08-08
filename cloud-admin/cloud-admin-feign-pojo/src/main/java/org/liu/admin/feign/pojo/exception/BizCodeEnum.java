package org.liu.admin.feign.pojo.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.justing.commons.exception.CodeEnum;

/**
 * admin服务的错误码以11开头
 */
@Getter
@AllArgsConstructor
public enum BizCodeEnum implements CodeEnum {

    MISSING_OPERATOR("110000", "操作员不存在"),
    ;

    private final String code;
    private final String msg;
}
