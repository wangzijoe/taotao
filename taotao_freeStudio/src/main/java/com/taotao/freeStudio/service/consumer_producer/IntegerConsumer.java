package com.taotao.freeStudio.service.consumer_producer;

import com.alibaba.fastjson.JSON;
import com.taotao.freeStudio.socket.constant.Constants;
import com.taotao.freeStudio.socket.handler.TWebSocketHandler;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;

import java.util.Set;

public class IntegerConsumer implements Runnable {

    private String jspSessionId;

    private IntegerStorage integerStorage;

    private TWebSocketHandler tWebSocketHandler;

    public IntegerConsumer(String jspSessionId, IntegerStorage integerStorage, TWebSocketHandler tWebSocketHandler) {
        this.jspSessionId = jspSessionId;
        this.integerStorage = integerStorage;
        this.tWebSocketHandler = tWebSocketHandler;
    }

    @Override
    public void run() {

        try {

            while (null != gainWebSocketSession(jspSessionId)) {
                Integer pop = integerStorage.pop();
                WebSocketSession webSocketSession = gainWebSocketSession(jspSessionId);
                if (null != webSocketSession)
                    webSocketSession.sendMessage(new TextMessage(JSON.toJSONString(pop)));
                Thread.sleep(1000);
                System.out.println("IntegerConsumer：" + Thread.currentThread().getName());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private WebSocketSession gainWebSocketSession(String sessionId) {
        Set<WebSocketSession> sessions = tWebSocketHandler.getSessions();
        for (WebSocketSession session : sessions) {
            if (sessionId.equals(session.getAttributes().get(Constants.JSP_SESSION_ID)))
                return session;
        }
        return null;
    }

}
