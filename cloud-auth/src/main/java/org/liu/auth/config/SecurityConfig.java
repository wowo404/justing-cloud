package org.liu.auth.config;

import org.liu.auth.service.SysUserDetailsService;
import org.liu.auth.service.WxUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * @Author lzs
 * @Date 2022/7/13 17:34
 **/
@Configuration
public class SecurityConfig {

    @Configuration
    @Order(1)
    static class SysSecurityConfig extends WebSecurityConfigurerAdapter {

        @Autowired
        private SysUserDetailsService sysUserDetailsService;

        @Override
        protected void configure(AuthenticationManagerBuilder auth) throws Exception {
            auth.userDetailsService(sysUserDetailsService);
        }

        @Bean
        @Override
        protected AuthenticationManager authenticationManager() throws Exception {
            return super.authenticationManager();
        }

        @Override
        protected void configure(HttpSecurity http) throws Exception {
            http.authorizeRequests().antMatchers("/ok").permitAll();
            super.configure(http);
        }
    }

    @Configuration
    @Order(2)
    static class WxSecurityConfig extends WebSecurityConfigurerAdapter {
        @Autowired
        private WxUserDetailsService wxUserDetailsService;

        @Override
        protected void configure(AuthenticationManagerBuilder auth) throws Exception {
            auth.userDetailsService(wxUserDetailsService);
        }

        @Bean
        @Override
        protected AuthenticationManager authenticationManager() throws Exception {
            return super.authenticationManager();
        }

        @Override
        protected void configure(HttpSecurity http) throws Exception {
            http.authorizeRequests().antMatchers("/ok").permitAll();
            super.configure(http);
        }
    }

}
