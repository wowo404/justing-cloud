package org.liu.common.cors;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.time.Duration;
import java.util.Arrays;

import static org.liu.common.core.constants.CommonConstants.*;

/**
 * 此处只需要提供一个CorsConfigurationSource类型的bean，spring security的CorsConfigurer会自动配置
 *
 * @Author lzs
 * @Date 2022/8/5 15:33
 **/
@RequiredArgsConstructor
@Configuration
public class CorsConfig {

    private final CorsProperties corsProperties;

    /**
     * cors官方文档：https://developer.mozilla.org/zh-CN/docs/Web/HTTP/CORS
     * 如果要携带身份凭证，如cookie，allowedOrigin,allowedHeader,allowedMethod都不能设置为*
     *
     * @return
     */
    @Bean
    public UrlBasedCorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration corsConfiguration = new CorsConfiguration();
        for (String allowedOrigin : corsProperties.getAllowedOrigins()) {
            corsConfiguration.addAllowedOrigin(allowedOrigin);
        }
        corsConfiguration.setAllowCredentials(true);
        corsConfiguration.setAllowedHeaders(Arrays.asList(HEADER_AUTHORIZATION, HEADER_CLIENT, HEADER_TENANT_ID));
        corsConfiguration.addAllowedMethod(HttpMethod.GET);
        corsConfiguration.addAllowedMethod(HttpMethod.HEAD);
        corsConfiguration.addAllowedMethod(HttpMethod.POST);
        corsConfiguration.addAllowedMethod(HttpMethod.PUT);
        corsConfiguration.addAllowedMethod(HttpMethod.DELETE);
        corsConfiguration.setMaxAge(Duration.ofHours(24));//在有效时间内，浏览器无须为同一请求再次发起预检请求

        UrlBasedCorsConfigurationSource corsConfigurationSource = new UrlBasedCorsConfigurationSource();
        corsConfigurationSource.registerCorsConfiguration("/**", corsConfiguration);
        return corsConfigurationSource;
    }

}
