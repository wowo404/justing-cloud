package org.liu.common.security.resource.server.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.TokenStore;

/**
 * @Author lzs
 * @Date 2022/7/5 8:52
 **/
@EnableResourceServer
@Configuration
public class ResourceServerConfig extends ResourceServerConfigurerAdapter {

    @Autowired
    private TokenStore tokenStore;
    @Value("${spring.application.name}")
    private String applicationName;

    @Override
    public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
        //未配置tokenService，默认使用的DefaultTokenServices
        resources.resourceId(applicationName).tokenStore(tokenStore);
    }

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers(HttpMethod.GET, "/operator/queryByUsername/**").permitAll()
                .antMatchers(HttpMethod.GET, "/wxUser/queryByJsCode/**").permitAll()
                .antMatchers("/ok").permitAll()
                .anyRequest().authenticated()
                .and().cors();
    }
    //如果此注释放开，则configure方法中配置tokenServices替代tokenStore
    // @Bean
    //    public DefaultTokenServices tokenServices(){
    //        DefaultTokenServices tokenServices = new DefaultTokenServices();
    //        tokenServices.setTokenStore(tokenStore);
    //        return tokenServices;
    //    }
}
