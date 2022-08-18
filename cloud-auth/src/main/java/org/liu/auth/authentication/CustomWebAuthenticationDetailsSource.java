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
        //TIPS：保留此段代码，只是为了学习
        //此处无法获取到这个缓存的请求：http://desktop-vr558d9:7474/oauth/authorize?client=PC&tenant-id=1&client_id=thirdparty-app&response_type=code&scope=all&redirect_uri=http://localhost:8082/index2.html
        //因为sessionId不一样了，为什么不一样？详细解释在语雀的流程图上
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
            if (null == tenantId) {
                String[] parameterValues = request.getParameterValues(HEADER_TENANT_ID);
                tenantId = null != parameterValues ? Long.parseLong(parameterValues[0]) : null;
            }
        }
        return new CustomWebAuthenticationDetails(context, client, tenantId);
    }
}
