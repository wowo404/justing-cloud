/**
 * bs
 */
package org.liu.wx.config;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.liu.common.core.constants.CommonConstants;
import org.liu.wx.feign.pojo.constants.WebSocketConstant;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.simp.config.ChannelRegistration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.messaging.simp.stomp.StompCommand;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.messaging.support.ChannelInterceptor;
import org.springframework.messaging.support.MessageHeaderAccessor;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

/**
 * WebSocket配置类
 */
@Slf4j
@Configuration
@RequiredArgsConstructor
@EnableWebSocketMessageBroker
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {

    private final TokenStore tokenStore;

    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        log.info("WebSocket服务器注册");
        registry.addEndpoint("/ws")
                .setAllowedOrigins("*")
                .withSockJS();
    }

    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry) {
        log.info("WebSocket服务器启动");
        registry.setUserDestinationPrefix(WebSocketConstant.USER_DESTINATION_PREFIX);
    }

    @Override
    public void configureClientInboundChannel(ChannelRegistration registration) {
        registration.interceptors(new ChannelInterceptor() {
            @Override
            public Message<?> preSend(Message<?> message, MessageChannel channel) {
                StompHeaderAccessor accessor = MessageHeaderAccessor.getAccessor(message, StompHeaderAccessor.class);
                if (null == accessor) {
                    return null;
                }
                // 判断是否首次连接请求
                if (StompCommand.CONNECT.equals(accessor.getCommand())) {
                    String tokens = accessor.getFirstNativeHeader(CommonConstants.HEADER_AUTHORIZATION);
                    log.info("webSocket token is {}", tokens);
                    if (StrUtil.isBlank(tokens)) {
                        return null;
                    }
                    // 验证令牌信息
                    OAuth2Authentication auth2Authentication = tokenStore.readAuthentication(tokens.split(" ")[1]);
                    if (ObjectUtil.isNotNull(auth2Authentication)) {
                        accessor.setUser(auth2Authentication);
                        return message;
                    } else {
                        return null;
                    }
                }
                //不是首次连接，已经成功登陆
                return message;
            }
        });
    }
}