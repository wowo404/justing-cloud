package org.liu.wx.feign.pojo.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.justing.commons.exception.CodeEnum;

/**
 * wx服务的错误码以24开头
 */
@Getter
@AllArgsConstructor
public enum BizCodeEnum implements CodeEnum {

    MISSING_HEADER_CLIENT("240000", "请求头client为必须的参数"),
    ;

    private final String code;
    private final String msg;
}
