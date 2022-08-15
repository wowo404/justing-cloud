package org.liu.auth.config;

import org.liu.common.security.base.pojo.BaseUser;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.stereotype.Component;

import java.util.LinkedHashMap;
import java.util.Map;

import static org.liu.common.core.constants.CommonConstants.*;

/**
 * @Author lzs
 * @Date 2022/7/12 17:12
 **/
@Component
public class CustomAdditionalInformation implements TokenEnhancer {
    @Override
    public OAuth2AccessToken enhance(OAuth2AccessToken accessToken, OAuth2Authentication authentication) {
        Map<String, Object> map = new LinkedHashMap<>();
        //client_credentials模式下principal是client_id
        if (authentication.getPrincipal() instanceof BaseUser) {
            BaseUser baseUser = (BaseUser) authentication.getPrincipal();
            map.put(ADDITIONAL_ID, baseUser.getId());
            map.put(ADDITIONAL_TENANT_ID, baseUser.getTenantId());
            map.put(ADDITIONAL_DISPLAY_NAME, baseUser.getDisplayName());
            map.put(ADDITIONAL_ROLE_IDS, baseUser.getRoleIds());
            map.put(ADDITIONAL_DATA_SCOPE, baseUser.getDataScope());
            map.put(ADDITIONAL_DEPT_IDS, baseUser.getDeptIds());
            ((DefaultOAuth2AccessToken) accessToken).setAdditionalInformation(map);
        }
        return accessToken;
    }
}
