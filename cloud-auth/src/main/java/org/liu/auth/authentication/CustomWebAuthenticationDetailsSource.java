package org.liu.auth.authentication;

import org.springframework.security.authentication.AuthenticationDetailsSource;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.security.web.savedrequest.RequestCache;
import org.springframework.security.web.savedrequest.SavedRequest;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

import static org.liu.common.core.constants.CommonConstants.HEADER_CLIENT;
import static org.liu.common.core.constants.CommonConstants.HEADER_TENANT_ID;

/**
 * @Author lzs
 * @Date 2022/8/15 16:15
 **/
@Component
public class CustomWebAuthenticationDetailsSource implements AuthenticationDetailsSource<HttpServletRequest, CustomWebAuthenticationDetails> {

    private RequestCache requestCache = new HttpSessionRequestCache();

    @Override
    public CustomWebAuthenticationDetails buildDetails(HttpServletRequest context) {
        System.out.println(context.getSession(false).getId());
        String client = context.getHeader(HEADER_CLIENT);
        if (!StringUtils.hasText(client)) {
            client = context.getParameter(HEADER_CLIENT);
        }
        Long tenantId = null;
        String tenantIdStr = context.getHeader(HEADER_TENANT_ID);
        if (StringUtils.hasText(tenantIdStr)) {
            tenantId = Long.parseLong(tenantIdStr);
        } else {
            tenantIdStr = context.getParameter(HEADER_TENANT_ID);
            if (StringUtils.hasText(tenantIdStr)) {
                tenantId = Long.parseLong(tenantIdStr);
            }
        }
        SavedRequest request = requestCache.getRequest(context, null);
        if (null != request) {
            List<String> clients = request.getHeaderValues(HEADER_CLIENT);
            if (!CollectionUtils.isEmpty(clients)) {
                client = clients.get(0);
            }
            if (null == client) {
                String[] parameterValues = request.getParameterValues(HEADER_CLIENT);
                client = null != parameterValues ? parameterValues[0] : null;
            }
            List<String> tenantIds = request.getHeaderValues(HEADER_TENANT_ID);
            if (!CollectionUtils.isEmpty(tenantIds)) {
                tenantId = Long.parseLong(tenantIds.get(0));
            }
            String[] parameterValues = request.getParameterValues(HEADER_TENANT_ID);
            tenantId = null != parameterValues ? Long.parseLong(parameterValues[0]) : null;
        }
        return new CustomWebAuthenticationDetails(context, client, tenantId);
    }
}
