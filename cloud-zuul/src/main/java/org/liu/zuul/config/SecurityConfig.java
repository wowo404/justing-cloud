package org.liu.zuul.config;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.security.oauth2.client.EnableOAuth2Sso;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.configurers.ExpressionUrlAuthorizationConfigurer;

/**
 * @Author lzs
 * @Date 2022/8/25 14:55
 **/
@RequiredArgsConstructor
@EnableOAuth2Sso
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final BaseAuthIgnoreProperties ignoreProperties;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        ExpressionUrlAuthorizationConfigurer<HttpSecurity>.ExpressionInterceptUrlRegistry requests = http.authorizeRequests();
        for (String url : ignoreProperties.getUrls()) {
            requests.antMatchers(url).permitAll();
        }
        requests.anyRequest().authenticated();
        http.csrf().disable();
    }
}
