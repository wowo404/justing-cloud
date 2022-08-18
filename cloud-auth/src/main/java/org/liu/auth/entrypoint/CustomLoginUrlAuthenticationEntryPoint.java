package org.liu.auth.entrypoint;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.LoginUrlAuthenticationEntryPoint;
import org.springframework.security.web.util.RedirectUrlBuilder;
import org.springframework.security.web.util.UrlUtils;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 自定义拒绝访问时redirect到登录url的entryPoint
 *
 * @Author lzs
 * @Date 2022/8/18 10:19
 **/
@Slf4j
@Component
public class CustomLoginUrlAuthenticationEntryPoint extends LoginUrlAuthenticationEntryPoint {

    public CustomLoginUrlAuthenticationEntryPoint() {
        this("/login");
    }

    public CustomLoginUrlAuthenticationEntryPoint(String loginFormUrl) {
        super(loginFormUrl);
    }

    @Override
    protected String buildRedirectUrlToLoginPage(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) {
        String loginForm = determineUrlToUseForThisRequest(request, response,
                authException);

        if (UrlUtils.isAbsoluteUrl(loginForm)) {
            return loginForm;
        }

        int serverPort = super.getPortResolver().getServerPort(request);
        String scheme = request.getScheme();

        RedirectUrlBuilder urlBuilder = new RedirectUrlBuilder();

        urlBuilder.setScheme(scheme);
        urlBuilder.setServerName(request.getServerName());
        urlBuilder.setPort(serverPort);
        urlBuilder.setContextPath(request.getContextPath());
        urlBuilder.setPathInfo(loginForm);
        urlBuilder.setQuery(request.getQueryString());

        if (super.isForceHttps() && "http".equals(scheme)) {
            Integer httpsPort = super.getPortMapper().lookupHttpsPort(serverPort);

            if (httpsPort != null) {
                // Overwrite scheme and port in the redirect URL
                urlBuilder.setScheme("https");
                urlBuilder.setPort(httpsPort);
            } else {
                log.warn("Unable to redirect to HTTPS as no port mapping found for HTTP port "
                        + serverPort);
            }
        }

        return urlBuilder.getUrl();
    }
}
