package org.liu.common.security.resource.server.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;

import static org.liu.common.core.constants.CommonConstants.SIGNING_KEY;

/**
 * @Author lzs
 * @Date 2022/7/4 16:11
 **/
@Configuration
public class AccessTokenConfig {

    @Autowired
    private CustomAccessTokenConverter accessTokenConverter;

    @Bean
    TokenStore tokenStore() {
        return new JwtTokenStore(jwtAccessTokenConverter());
    }

    @Bean
    JwtAccessTokenConverter jwtAccessTokenConverter() {
        JwtAccessTokenConverter converter = new JwtAccessTokenConverter();
        converter.setAccessTokenConverter(accessTokenConverter);
        converter.setSigningKey(SIGNING_KEY);
        return converter;
    }

}
