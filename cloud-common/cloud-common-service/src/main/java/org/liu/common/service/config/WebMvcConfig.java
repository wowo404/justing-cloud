package org.liu.common.service.config;

import lombok.RequiredArgsConstructor;
import org.liu.common.service.interceptor.BaseHandlerInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @Author lzs
 * @Date 2022/8/9 10:22
 **/
@RequiredArgsConstructor
@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    private final BaseHandlerInterceptor baseHandlerInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(baseHandlerInterceptor).addPathPatterns("/**");
    }
}
