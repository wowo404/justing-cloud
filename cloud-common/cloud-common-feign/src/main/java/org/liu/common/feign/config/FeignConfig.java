package org.liu.common.feign.config;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.StringUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

import static org.liu.common.core.constants.CommonConstants.*;

@Slf4j
@Configuration
public class FeignConfig implements RequestInterceptor {
//不需要在此处配置，配置文件中配置即可
//    @Bean
//    Logger.Level feignLoggerLevel() {
//        return Logger.Level.FULL;
//    }
//
//    @Bean
//    public Contract feignContract() {
//        return new Contract.Default();
//    }
//
//    @Bean
//    public Retryer retryer() {
//        return new Retryer.Default();
//    }

//    @Bean
//    public ErrorDecoder errorDecoder(ObjectMapper objectMapper) {
//        return new CustomErrorDecoder(objectMapper);
//    }
//
    //异常处理，默认的异常处理是
//    @Slf4j
//    @RequiredArgsConstructor
//    public static class CustomErrorDecoder implements ErrorDecoder {
//
//        private final ObjectMapper objectMapper;
//
//        @Override
//        public Exception decode(String methodKey, Response response) {
//            try {
//                String body = Util.toString(response.body().asReader(Charset.defaultCharset()));
//                JsonNode jsonNode = objectMapper.readTree(body);
//                JsonNode exception = jsonNode.get("exception");
//                JsonNode trace = jsonNode.get("trace");
//                if (null != exception && null != trace) {
//                    log.info("trace from provider:{}", trace);
//                    if (trace.asText().contains("resultCode=") && trace.asText().contains(")")) {
//                        String exceptionEnumName = trace.asText().substring(trace.asText().indexOf("=") + 1, trace.asText().indexOf(")"));
//                        log.info("parse exception enum name is: {}", exceptionEnumName);
//                        return new BusinessException(ResultCode.valueOf(exceptionEnumName));
//                    }
//                }
//                return new BusinessException(ResultCode.FAIL);
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//            return null;
//        }
//    }

    //TIPS：oauth2有一个实现OAuth2FeignRequestInterceptor
/*    @Bean
    RequestInterceptor oauth2FeignRequestInterceptor() {
        return new OAuth2FeignRequestInterceptor(new DefaultOAuth2ClientContext(), resource());
    }

    private OAuth2ProtectedResourceDetails resource() {
        ResourceOwnerPasswordResourceDetails resourceDetails = new ResourceOwnerPasswordResourceDetails();
        resourceDetails.setUsername("piomin");
        resourceDetails.setPassword("piot123");
        resourceDetails.setAccessTokenUri(accessTokenUri);
        resourceDetails.setClientId(clientId);
        resourceDetails.setClientSecret(clientSecret);
        resourceDetails.setGrantType("password");
        resourceDetails.setScope(Arrays.asList(scope));
        return resourceDetails;
    }*/

    /**
     * 此方法的主要功能是对请求参数做处理
     * 比如：将登录用户的信息放入请求头
     *
     * @param template
     */
    @Override
    public void apply(RequestTemplate template) {
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        if (null == requestAttributes) {
            log.warn("未获取到ServletRequestAttributes");
            return;
        }
        HttpServletRequest request = requestAttributes.getRequest();
        //请求头：Authorization
        setAuthorizationIfExists(request, template);
        //请求头：client
        setClientIfExists(request, template);
        //请求头：tenantId
        setTenantIdIfExists(request, template);
    }

    private void setTenantIdIfExists(HttpServletRequest request, RequestTemplate template) {
        String client = request.getHeader(HEADER_CLIENT);
        if (!StringUtils.hasText(client)) {
            client = request.getParameter(HEADER_CLIENT);
        }
        if (StringUtils.hasText(client)) {
            template.header(HEADER_CLIENT, client);
        }
    }

    private void setClientIfExists(HttpServletRequest request, RequestTemplate template) {
        String tenantId = request.getHeader(HEADER_TENANT_ID);
        if (!StringUtils.hasText(tenantId)) {
            tenantId = request.getParameter(HEADER_TENANT_ID);
        }
        if (StringUtils.hasText(tenantId)) {
            template.header(HEADER_TENANT_ID, tenantId);
        }
    }

    private void setAuthorizationIfExists(HttpServletRequest request, RequestTemplate template) {
        String authorization = request.getHeader(HEADER_AUTHORIZATION);
        if (!StringUtils.hasText(authorization)) {
            authorization = request.getParameter(HEADER_AUTHORIZATION);
        }
        if (!StringUtils.hasText(authorization)) {
            log.warn("没有获取到authorization，请求uri：{}", request.getRequestURI());
            return;
        }
        //把authorization原样放入feign请求头，符合oauth2资源服务端规范
        template.header(HEADER_AUTHORIZATION, authorization);
    }

}
