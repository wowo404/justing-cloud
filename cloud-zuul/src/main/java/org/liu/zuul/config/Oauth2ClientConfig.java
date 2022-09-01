package org.liu.zuul.config;

import org.springframework.boot.autoconfigure.security.oauth2.client.EnableOAuth2Sso;
import org.springframework.boot.autoconfigure.security.oauth2.resource.UserInfoRestTemplateFactory;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;

/**
 * @Author lzs
 * @Date 2022/8/26 16:28
 **/
@EnableOAuth2Sso
@Configuration
public class Oauth2ClientConfig {
    @Bean
    @LoadBalanced
    public OAuth2RestTemplate loadBalancedRestTemplate(UserInfoRestTemplateFactory restTemplateFactory) {
        return restTemplateFactory.getUserInfoRestTemplate();
    }
}
