package org.liu.auth.config;

import org.liu.auth.service.SysUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * @Author lzs
 * @Date 2022/7/13 17:34
 **/
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private SysUserDetailsService sysUserDetailsService;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /**
     * TIPS:保留此注释掉的代码只为学习
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
//        CustomAuthenticationProvider customAuthenticationProvider = new CustomAuthenticationProvider();
//        customAuthenticationProvider.setPasswordEncoder(passwordEncoder());
//        customAuthenticationProvider.setUserDetailsServices(Arrays.asList(sysUserDetailsService, wxUserDetailsService));
//        auth.authenticationProvider(customAuthenticationProvider);
        auth.userDetailsService(sysUserDetailsService);
    }

    @Bean
    @Override
    protected AuthenticationManager authenticationManager() throws Exception {
        return super.authenticationManager();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/ok").permitAll()
                //微信服务的认证流程让微信服务自身实现
                .antMatchers("/wxApi/**").permitAll();
        super.configure(http);
        http.cors();
    }

}
