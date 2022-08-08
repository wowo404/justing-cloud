package org.liu.auth.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.filter.RequestContextFilter;
import org.springframework.web.servlet.DispatcherServlet;

import javax.annotation.PostConstruct;

/**
 * @Author lzs
 * @Date 2022/8/8 11:06
 **/
@Configuration
public class WebMvcConfig {

    @Autowired
    private RequestContextFilter requestContextFilter;

    @Autowired
    private DispatcherServlet dispatcherServlet;

    @PostConstruct
    public void init() {
        requestContextFilter.setThreadContextInheritable(true);
        dispatcherServlet.setThreadContextInheritable(true);
    }
}
