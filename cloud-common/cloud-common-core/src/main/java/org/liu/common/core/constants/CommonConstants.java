package org.liu.common.core.constants;

/**
 * 各种常量
 */
public interface CommonConstants {
    //授权请求头名称
    String HEADER_AUTHORIZATION = "Authorization";
    //客户端类型，见枚举ClientEnum
    String HEADER_CLIENT = "client";
    String HEADER_TENANT_ID = "tenant-id";//用横线分隔，跟其他标准请求头的风格保持一致

    Long SUPER_TENANT = 1L;
    Long SUPER_OPERATOR = 1L;
    Long SUPER_ROLE = 1L;
    String SUPER_SYS_OPERATOR_USERNAME = "admin";
    String SUPER_SYS_ROLE_NAME = "admin";
    String SUPER_SYS_TENANT_NAME = "admin";
    //放入accessToken的additionalInformation中的字段名
    String ADDITIONAL_ID = "id";
    String ADDITIONAL_TENANT_ID = "tenant_id";
    String ADDITIONAL_DISPLAY_NAME = "display_name";
    String ADDITIONAL_ROLE_IDS = "role_ids";
    String ADDITIONAL_DATA_SCOPE = "data_scope";
    String ADDITIONAL_DEPT_IDS = "dept_ids";
    String SIGNING_KEY = "JustingLiu";
}
