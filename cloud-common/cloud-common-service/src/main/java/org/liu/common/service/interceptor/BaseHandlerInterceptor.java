package org.liu.common.service.interceptor;

import cn.hutool.core.util.StrUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.liu.common.core.constants.CommonConstants;
import org.liu.common.service.tenant.TenantContextHolder;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * @Author lzs
 * @Date 2022/8/9 9:58
 **/
@Slf4j
@RequiredArgsConstructor
@Component
public class BaseHandlerInterceptor implements HandlerInterceptor {

    private final TokenStore tokenStore;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String tenantId = request.getHeader(CommonConstants.HEADER_TENANT_ID);
        if (StrUtil.isBlank(tenantId)) {
            //请求头中没有则去accessToken中解析
            String accessToken = request.getHeader(CommonConstants.HEADER_AUTHORIZATION);
            if (StrUtil.isNotBlank(accessToken)) {
                OAuth2Authentication oAuth2Authentication = tokenStore.readAuthentication(accessToken);
                Map<String, Object> details = (Map<String, Object>) oAuth2Authentication.getDetails();
                TenantContextHolder.setTenantId(Long.parseLong(String.valueOf(details.get(CommonConstants.ADDITIONAL_TENANT_ID))));
            } else {
                log.warn("没有从请求头中获取到tenant-id参数，也没有从token中解析出tenant_id，uri={}", request.getRequestURI());
            }
        } else {
            TenantContextHolder.setTenantId(Long.parseLong(tenantId));
        }
        return true;
    }
}
