package com.kinoymir.chat.config.websocket;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.AbstractWebSocketMessageBrokerConfigurer;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;

@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig extends AbstractWebSocketMessageBrokerConfigurer {
    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        registry.addEndpoint("/webSocketServer").setAllowedOrigins("*").withSockJS();
    }

    @Override
    public void configureMessageBroker(MessageBrokerRegistry config) {
        /*
          表示客户端订阅地址的前缀信息，也就是客户端接收服务端消息的地址的前缀信息
         */
        config.enableSimpleBroker("/topic", "/queue");
        /*
          指服务端接收地址的前缀，意思就是说客户端给服务端发消息的地址的前缀
         */
        config.setApplicationDestinationPrefixes("/app");
        /*
          点对点使用的订阅前缀（客户端订阅路径上会体现出来），不设置的话，默认也是/user/
         */
        config.setUserDestinationPrefix("/user/");
    }

    @Bean
    public SocketSessionRegistry SocketSessionRegistry() {
        return new SocketSessionRegistry();
    }

    @Bean
    public STOMPConnectEventListener STOMPConnectEventListener() {
        return new STOMPConnectEventListener();
    }

}
