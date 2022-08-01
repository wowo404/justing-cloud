package org.liu.common.security.resource.server.config;

import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.DefaultAccessTokenConverter;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * @Author lzs
 * @Date 2022/8/1 15:53
 **/
@Component
public class CustomAccessTokenConverter extends DefaultAccessTokenConverter {
    @Override
    public OAuth2Authentication extractAuthentication(Map<String, ?> map) {
        OAuth2Authentication authentication = super.extractAuthentication(map);
        authentication.setDetails(map);
        return authentication;
    }
}
