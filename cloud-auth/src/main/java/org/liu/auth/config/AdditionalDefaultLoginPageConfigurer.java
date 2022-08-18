package org.liu.auth.config;

import org.springframework.security.config.annotation.web.HttpSecurityBuilder;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.DefaultLoginPageConfigurer;
import org.springframework.security.web.authentication.ui.DefaultLoginPageGeneratingFilter;
import org.springframework.security.web.authentication.ui.DefaultLogoutPageGeneratingFilter;
import org.springframework.security.web.csrf.CsrfToken;

import javax.servlet.http.HttpServletRequest;
import java.util.Collections;
import java.util.Map;
import java.util.function.Function;

/**
 * @Author lzs
 * @Date 2022/8/18 17:24
 **/
public class AdditionalDefaultLoginPageConfigurer<H extends HttpSecurityBuilder<H>> extends
        AbstractHttpConfigurer<DefaultLoginPageConfigurer<H>, H> {

    private DefaultLogoutPageGeneratingFilter logoutPageGeneratingFilter = new DefaultLogoutPageGeneratingFilter();

    @Override
    public void init(H http) {
        Function<HttpServletRequest, Map<String, String>> hiddenInputs = request -> {
            CsrfToken token = (CsrfToken) request.getAttribute(CsrfToken.class.getName());
            if (token == null) {
                return Collections.emptyMap();
            }
            return Collections.singletonMap(token.getParameterName(), token.getToken());
        };
        this.logoutPageGeneratingFilter.setResolveHiddenInputs(hiddenInputs);
        http.setSharedObject(DefaultLogoutPageGeneratingFilter.class,
                logoutPageGeneratingFilter);
    }

    @Override
    @SuppressWarnings("unchecked")
    public void configure(H http) {
        DefaultLoginPageGeneratingFilter filter = http.getSharedObject(DefaultLoginPageGeneratingFilter.class);
        if (filter.isEnabled()) {
            filter = postProcess(filter);
            http.addFilter(filter);
            http.addFilter(this.logoutPageGeneratingFilter);
        }
    }

}
