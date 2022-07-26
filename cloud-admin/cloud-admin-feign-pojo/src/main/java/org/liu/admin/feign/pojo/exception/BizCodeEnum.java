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

    MISSING_HEADER_CLIENT("110000", "请求头client为必须的参数"),
    ;

    private final String code;
    private final String msg;
}
