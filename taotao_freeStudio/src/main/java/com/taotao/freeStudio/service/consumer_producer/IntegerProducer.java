package com.taotao.freeStudio.service.consumer_producer;

import com.taotao.freeStudio.socket.constant.Constants;
import org.springframework.web.socket.WebSocketSession;

import java.util.Random;

public class IntegerProducer implements Runnable{

    private IntegerStorage integerStorage;

    private WebSocketSession webSocketSession;

    public IntegerProducer(IntegerStorage integerStorage, WebSocketSession webSocketSession) {
        this.integerStorage = integerStorage;
        this.webSocketSession = webSocketSession;
    }

    @Override
    public void run() {

        try {

            String jspSessionId = String.valueOf(webSocketSession.getAttributes().get(Constants.JSP_SESSION_ID));
            while(JSPRunningMap.getRunStatus(jspSessionId)){
                int randomInt = Integer.parseInt(String.format("%02d", new Random().nextInt(99)));
                integerStorage.push(randomInt);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
