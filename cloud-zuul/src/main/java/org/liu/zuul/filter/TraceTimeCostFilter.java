package org.liu.zuul.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants;
import org.springframework.stereotype.Component;

import java.util.Date;

@Slf4j
@Component
public class TraceTimeCostFilter extends ZuulFilter {
    @Override
    public String filterType() {
        return FilterConstants.POST_TYPE;
    }

    @Override
    public int filterOrder() {
        return 10;
    }

    @Override
    public boolean shouldFilter() {
        RequestContext context = RequestContext.getCurrentContext();
        Object callBackendStartObj = context.get("callBackendStart");
        if (null != callBackendStartObj) {
            return true;
        }
        return false;
    }

    @Override
    public Object run() throws ZuulException {
        RequestContext context = RequestContext.getCurrentContext();
        Date callBackendStart = (Date) context.get("callBackendStart");
        Date now = new Date();
        log.info("调用后端服务耗时={}ms", now.getTime() - callBackendStart.getTime());
        context.set("callBackendStart", null);
        return null;
    }
}
