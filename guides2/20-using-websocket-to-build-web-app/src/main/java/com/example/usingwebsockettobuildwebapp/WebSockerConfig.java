package com.example.usingwebsockettobuildwebapp;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

/**
 * @EnableWebSocketMessageBroker: enables WebSocket message hanlding, backed by a message broker.
 */
@Configuration
@EnableWebSocketMessageBroker
public class WebSockerConfig implements
        WebSocketMessageBrokerConfigurer {

    /**
     * This menthod configures message broker.
     *  config.enableSimpleBroker("/topic"); // enable a simple memory-based message broker to
     *  carry the greeting messages back to the client on destinations prefixed with `/topic`.
     *
     *    config.setApplicationDestinationPrefixes("/app"); // designate the `/app` prefix for
     *    messages that are bound for methods annotated with `@MessageMapping`
     *
     * @param config
     */
    @Override
    public void configureMessageBroker(MessageBrokerRegistry config) {
        config.enableSimpleBroker("/topic");
        config.setApplicationDestinationPrefixes("/app");
    }

    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        registry.addEndpoint("/gs-guide-websocket").withSockJS();
    }

}
