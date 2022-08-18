package org.liu.auth.config;

import org.liu.auth.authentication.CustomWebAuthenticationDetailsSource;
import org.liu.auth.entrypoint.CustomLoginUrlAuthenticationEntryPoint;
import org.liu.auth.provider.CustomAuthenticationProvider;
import org.liu.auth.service.SysUserDetailsService;
import org.liu.auth.service.WxUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.ObjectPostProcessor;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.ui.DefaultLoginPageGeneratingFilter;
import org.springframework.security.web.csrf.CsrfToken;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

/**
 * @Author lzs
 * @Date 2022/7/13 17:34
 **/
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private SysUserDetailsService sysUserDetailsService;
    @Autowired
    private WxUserDetailsService wxUserDetailsService;
    @Autowired
    private CustomWebAuthenticationDetailsSource customWebAuthenticationDetailsSource;
    @Autowired
    private CustomLoginUrlAuthenticationEntryPoint customLoginUrlAuthenticationEntryPoint;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /**
     * 只需要提供一个bean即可，ExceptionHandlingConfigurer#getRequestCache方法会优先使用容器中的bean
     *
     * @return
     */
//    @Bean
//    public HttpSessionRequestCache httpSessionRequestCache() {
//        HttpSessionRequestCache requestCache = new HttpSessionRequestCache();
//        requestCache.setCreateSessionAllowed(false);
//        return requestCache;
//    }
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        CustomAuthenticationProvider customAuthenticationProvider = new CustomAuthenticationProvider();
        customAuthenticationProvider.setPasswordEncoder(passwordEncoder());
        customAuthenticationProvider.setUserDetailsServices(Arrays.asList(sysUserDetailsService, wxUserDetailsService));
        auth.authenticationProvider(customAuthenticationProvider);
    }

    @Bean
    @Override
    protected AuthenticationManager authenticationManager() throws Exception {
        return super.authenticationManager();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests().antMatchers("/ok").permitAll()
                //手动设置/login这个url为permitAll，ant风格匹配，因为formLogin的permitAll是全路径匹配
//                .antMatchers("/login").permitAll()
                .anyRequest().authenticated();
        //formLogin这里不要使用permitAll，内部使用的PermitAllSupport.permitAll方法匹配的全路径（uri+queryString）
        http.formLogin().authenticationDetailsSource(customWebAuthenticationDetailsSource).permitAll()
                .withObjectPostProcessor(new ObjectPostProcessor<DefaultLoginPageGeneratingFilter>() {
                    //把login链接上的请求参数都放入隐藏域
                    @Override
                    public <O extends DefaultLoginPageGeneratingFilter> O postProcess(O object) {
                        Map<String, String> map = new HashMap<>();
                        Function<HttpServletRequest, Map<String, String>> hiddenInputs = request -> {
                            CsrfToken token = (CsrfToken) request.getAttribute(CsrfToken.class.getName());
                            if (token != null) {
                                map.put(token.getParameterName(), token.getToken());
                            }
                            Enumeration<String> parameterNames = request.getParameterNames();
                            while (parameterNames.hasMoreElements()) {
                                String paramName = parameterNames.nextElement();
                                String paramValue = request.getParameter(paramName);
                                map.put(paramName, paramValue);
                            }
                            return map;
                        };
                        object.setResolveHiddenInputs(hiddenInputs);
                        return object;
                    }
                })
                .and().apply(new AdditionalDefaultLoginPageConfigurer<>());
        http.httpBasic();
        //手动配置authenticationEntryPoint后会使DefaultLoginPageGeneratingFilter和DefaultLogoutPageGeneratingFilter失效
        //因为DefaultLoginPageConfigurer.configure方法有个authenticationEntryPoint == null的判断
        http.exceptionHandling().authenticationEntryPoint(customLoginUrlAuthenticationEntryPoint);
    }

}
