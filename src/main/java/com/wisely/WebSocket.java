package com.wisely;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.AbstractWebSocketMessageBrokerConfigurer;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;

/**
 * Created by gaowenfeng on 2017/2/5.
 */
@Configuration
/**
 * 通过@EnableWebSocketMessageBroker注解开启使用STOMP协议来传输基于代理(message broker)的消息,
 * 这是控制器支持使用@MessageMapping,就像使用@RequestMapping一样
 */
@EnableWebSocketMessageBroker
public class WebSocket extends AbstractWebSocketMessageBrokerConfigurer{
    /**
     * 注册STOMP协议的节点(endpoint),并映射的制定的URL
     * @param stompEndpointRegistry
     */
    @Override
    public void registerStompEndpoints(StompEndpointRegistry stompEndpointRegistry) {
        stompEndpointRegistry.addEndpoint("/endpointChat").withSockJS();    //注册一个名为/endpointChat的endpoint,并指定使用SockJs协议。
    }

    /**
     * 配置消息代理
     * @param registry
     */
    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry) {
        registry.enableSimpleBroker("/queue");                     //点对点式应增加一个/queue消息代理
    }
}
