package org.liu.common.service.interceptor;

import cn.hutool.core.util.StrUtil;
import lombok.extern.slf4j.Slf4j;
import org.liu.common.core.constants.CommonConstants;
import org.liu.common.service.tenant.TenantContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Author lzs
 * @Date 2022/8/9 9:58
 **/
@Slf4j
@Component
public class BaseHandlerInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String tenantId = request.getHeader(CommonConstants.HEADER_TENANT_ID);
        if (StrUtil.isBlank(tenantId)) {
            log.warn("请求头中缺少tenant-id参数，uri={}", request.getRequestURI());
        } else {
            TenantContextHolder.setTenantId(Long.parseLong(tenantId));
        }
        return true;
    }
}
