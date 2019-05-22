package com.taotao.freeStudio.socket.handler;

import lombok.Getter;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.io.IOException;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.concurrent.ConcurrentSkipListSet;

@Component
@Getter
public class TWebSocketHandler extends TextWebSocketHandler {

    private Set<WebSocketSession> sessions = new LinkedHashSet<>();

    //连接已建立
    public void afterConnectionEstablished(WebSocketSession session)
            throws Exception {
        sessions.add(session);
    }

    //消息接收处理
    public void handleMessage(WebSocketSession session, WebSocketMessage<?> ws_msg)
            throws Exception {
        //消息处理
    }

    //连接已关闭
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status)
            throws Exception {
        sessions.remove(session);
    }


    //异常处理
    public void handleTransportError(WebSocketSession session, Throwable e)
            throws Exception {
        if(session.isOpen()) session.close();
        sessions.remove(session);
    }

    public boolean supportsPartialMessages() {
        return false;
    }

    /**
     * 自定义接口
     * 给所有在线用户发送消息
     * @param message
     * @throws IOException
     */
    public void sendMessageToUsers(TextMessage message) throws IOException {
        for (WebSocketSession user : sessions) {
            if (user.isOpen()) user.sendMessage(message);
        }
    }

}
