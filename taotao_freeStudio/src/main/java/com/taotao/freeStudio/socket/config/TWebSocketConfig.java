package com.taotao.freeStudio.socket.config;

import com.taotao.freeStudio.socket.handler.TWebSocketHandler;
import com.taotao.freeStudio.socket.intercepter.TWebSocketHandshakeInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;


@Configuration
@EnableWebSocket
@EnableWebMvc
public class TWebSocketConfig implements WebSocketConfigurer {

    public void registerWebSocketHandlers(WebSocketHandlerRegistry webSocketHandlerRegistry) {
        //这里的url要与页面的url一致
        webSocketHandlerRegistry
                .addHandler(new TWebSocketHandler(), "/webSocket/tWebSocket.action")
                .addInterceptors(new TWebSocketHandshakeInterceptor());
        //至于这里为什么要加info，我遇见的情况是，当我使用sockjs来代替websocket时，连接的后面会自动加上info
        webSocketHandlerRegistry
                .addHandler(new TWebSocketHandler(), "/webSocket/sockJs/tWebSocket/info")
                .addInterceptors(new TWebSocketHandshakeInterceptor()).withSockJS();
    }

}
