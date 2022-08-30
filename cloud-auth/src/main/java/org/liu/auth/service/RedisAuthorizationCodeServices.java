package org.liu.auth.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.liu.common.cache.RedisHelper;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.code.RandomValueAuthorizationCodeServices;
import org.springframework.stereotype.Component;

import static org.liu.common.cache.RedisCode.OAUTH2_AUTHORIZATION_CODE;

/**
 * @Author lzs
 * @Date 2022/8/30 9:32
 **/
@RequiredArgsConstructor
@Slf4j
@Component
public class RedisAuthorizationCodeServices extends RandomValueAuthorizationCodeServices {

    private final RedisHelper redisHelper;

    @Override
    protected void store(String code, OAuth2Authentication authentication) {
        redisHelper.saveOauth2AuthorizationCode(OAUTH2_AUTHORIZATION_CODE, code, authentication);
    }

    @Override
    protected OAuth2Authentication remove(String code) {
        Object value = redisHelper.deleteOauth2AuthorizationCode(OAUTH2_AUTHORIZATION_CODE, code);
        return null == value ? null : (OAuth2Authentication) value;
    }
}
