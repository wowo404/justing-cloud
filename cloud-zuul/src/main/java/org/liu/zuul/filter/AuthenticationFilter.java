package org.liu.zuul.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang.StringUtils;
import org.apache.http.HttpStatus;
import org.liu.zuul.config.BaseAuthIgnoreProperties;
import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

@RequiredArgsConstructor
@Component
public class AuthenticationFilter extends ZuulFilter {

    private final BaseAuthIgnoreProperties baseAuthIgnoreProperties;
    private static AntPathMatcher antPathMatcher = new AntPathMatcher();

    /**
     * pre-请求在被路由之前执行
     * route-在路由请求时调用
     * post-在route和error过滤器之后调用
     * error-处理请求时发生错误调用
     */
    @Override
    public String filterType() {
        return FilterConstants.PRE_TYPE;
    }

    /**
     * 数字越小优先级越高
     */
    @Override
    public int filterOrder() {
        return 10;
    }

    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Override
    public Object run() throws ZuulException {
        RequestContext context = RequestContext.getCurrentContext();
        HttpServletRequest request = context.getRequest();
        String authorization = request.getHeader("Authorization");
        boolean isIgnoreUrl = isIgnoreUrl(request.getRequestURI());
        if (!isIgnoreUrl && StringUtils.isBlank(authorization)) {
            context.setSendZuulResponse(false);
            context.setResponseStatusCode(HttpStatus.SC_UNAUTHORIZED);
            context.setResponseBody("");
        } else {
            //验证通过，可以做些其他操作，比如往请求头放些需要放入的公共参数之类的
        }
        context.set("callBackendStart", new Date());
        return null;
    }

    private boolean isIgnoreUrl(String requestURI) {
        boolean empty = baseAuthIgnoreProperties.getUrls().isEmpty();
        if (empty) {
            return false;
        }
        for (String ignoreAuthorizationUrl : baseAuthIgnoreProperties.getUrls()) {
            boolean match = antPathMatcher.match(ignoreAuthorizationUrl, requestURI);
            if (match) {
                return true;
            }
        }
        return false;
    }
}
