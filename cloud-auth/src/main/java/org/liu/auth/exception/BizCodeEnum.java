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
    HEADER_CLIENT_NOT_SUPPORT("120001", "不支持的请求头client"),
    ERROR_GET_OPERATOR_DETAIL("120002", "获取操作员详情错误"),
    ROLE_MUST_HAVE_DATA_SCOPE("120003", "角色必须设置数据权限范围"),
    ;

    private final String code;
    private final String msg;
}
