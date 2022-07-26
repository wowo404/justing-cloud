package org.liu.admin.feign.pojo.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum CredentialsExpiredEnum {
    NO(0, "否"), YES(1, "是");

    private final Integer code;
    private final String msg;
}
