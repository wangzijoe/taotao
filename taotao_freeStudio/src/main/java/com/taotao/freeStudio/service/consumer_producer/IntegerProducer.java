package com.taotao.freeStudio.service.consumer_producer;

import com.taotao.freeStudio.socket.constant.Constants;
import com.taotao.freeStudio.socket.handler.TWebSocketHandler;
import org.springframework.web.socket.WebSocketSession;

import java.util.Random;
import java.util.Set;

public class IntegerProducer implements Runnable {

    private String jspSessionId;

    private IntegerStorage integerStorage;

    private TWebSocketHandler tWebSocketHandler;


    public IntegerProducer(String jspSessionId, IntegerStorage integerStorage, TWebSocketHandler tWebSocketHandler) {
        this.jspSessionId = jspSessionId;
        this.integerStorage = integerStorage;
        this.tWebSocketHandler = tWebSocketHandler;
    }

    @Override
    public void run() {
        try {
            while (null != gainWebSocketSession(jspSessionId)) {
                for (int i = 0; i < 10; i++) {
                    int randomInt = Integer.parseInt(String.format("%02d", new Random().nextInt(99)));
                    System.out.println("IntegerProducer >>> storage :" + Thread.currentThread().getName());
                    integerStorage.push(randomInt);
                }
                Thread.sleep(5000);
            }

        } catch (InterruptedException e) {
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
