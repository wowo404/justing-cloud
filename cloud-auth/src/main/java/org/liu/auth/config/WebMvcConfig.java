package org.liu.auth.config;

/**
 * @Author lzs
 * @Date 2022/8/8 11:06
 **/
//@Configuration
public class WebMvcConfig {
    //此配置是为了解决RequestInterceptor无法获取ServletRequestAttributes，这是一个解决方案，
    // 另一个解决方案是配置hystrix.command.default.execution.isolation.strategy=SEMAPHORE
//
//    @Autowired
//    private RequestContextFilter requestContextFilter;
//
//    @Autowired
//    private DispatcherServlet dispatcherServlet;
//
//    @PostConstruct
//    public void init() {
//        requestContextFilter.setThreadContextInheritable(true);
//        dispatcherServlet.setThreadContextInheritable(true);
//    }
}
