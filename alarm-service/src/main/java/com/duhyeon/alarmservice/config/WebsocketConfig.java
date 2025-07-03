package com.duhyeon.alarmservice.config;

import com.duhyeon.alarmservice.handler.WebsocketHandler;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.*;

@Configuration
@EnableWebSocket
public class WebsocketConfig implements WebSocketConfigurer {

    private final WebsocketHandler websocketHandler;

    public WebsocketConfig(WebsocketHandler websocketHandler) {
        this.websocketHandler = websocketHandler;
    }

    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        registry.addHandler(websocketHandler, "/ws-endpoint")
                .addInterceptors(new IpHandshakeInterceptor())
                .setAllowedOriginPatterns("*");

    }
}
