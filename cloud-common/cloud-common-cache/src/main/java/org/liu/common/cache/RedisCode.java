package org.liu.common.cache;

import java.time.Duration;

/**
 * @date ：Created in 2019/3/1 13:31
 * @description：redis常量
 * @modified By：JustingLiu
 * @version: v1.0.0
 */
public interface RedisCode {
    String CAPTCHA = "CAPTCHA_";
    String CODE = "CODE_";
    String CODE_DAY_LIMIT = "CODE_DAY_LIMIT_";//格式：key+年月日，存放手机号码的每日发送次数：数据结构：set
    String TOKEN = "TOKEN_";
    String CONFIG = "CONFIG_";
    String SYSTEM_CONFIG = "system_config";

    String LOCK_UPDATE_ORDER_INFO = "lock_update_order_info_";//订单锁，格式：key+订单ID
    String LOCK_SKU = "lock_sku_";//sku锁，格式：key+skuId

    String WEIXIN_ACCESS_TOKEN = "weixin_access_token";//微信的AccessToken，数据类型：string，保存时间7200
    String WEIXIN_JSAPI_TICKET = "weixin_jsapi_ticket";//微信的JsAPI_Ticket，数据类型：string，保存时间7200
	String TODAY_QRCODE_CODE = "today_qrcode_code_";//qrCode递增编码，每日从1开始递增
    String OAUTH2_AUTHORIZATION_CODE = "oauth2_authorization_code";

    //有效期
    Duration CODE_EXPIRE_TIME = Duration.ofSeconds(5 * 60L);
    Duration TOKEN_EXPIRE_TIME = Duration.ofSeconds(7 * 24 * 60 * 60L);
    Duration LOCK_EXPIRE_TIME = Duration.ofSeconds(6);//锁的失效时间
    Duration WEIXIN_ACCESS_TOKEN_TIME = Duration.ofSeconds(2 * 60 * 60L);//微信AccessToken失效时间
    Duration WEIXIN_JSAPI_TICKET_TIME = Duration.ofSeconds(2 * 60 * 60L);//微信JsApiTicket失效时间
	Long TODAY_CODE_EXPIRE_TIME = Duration.ofDays(1L).getSeconds();//每日递增编码失效时间
}
