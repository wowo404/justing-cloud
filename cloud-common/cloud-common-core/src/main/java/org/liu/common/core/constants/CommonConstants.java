package org.liu.common.core.constants;

/**
 * 各种常量
 */
public interface CommonConstants {
    //授权请求头名称
    String HEADER_AUTHORIZATION = "Authorization";
    //客户端类型，见枚举ClientEnum
    String HEADER_CLIENT = "client";
    Long SUPER_TENANT = 1L;
    Long SUPER_OPERATOR = 1L;
    Long SUPER_ROLE = 1L;
    //放入accessToken的additionalInformation中的字段名
    String ADDITIONAL_ID = "id";
    String ADDITIONAL_TENANT_ID = "tenant_id";
    String ADDITIONAL_CLIENT = "client";
    String ADDITIONAL_DISPLAY_NAME = "display_name";
    String ADDITIONAL_ROLE_IDS = "role_ids";
    String ADDITIONAL_DATA_SCOPE = "data_scope";
    String ADDITIONAL_DEPT_IDS = "dept_ids";
    String SIGNING_KEY = "JustingLiu";
}
