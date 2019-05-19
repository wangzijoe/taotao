package com.taotao.freeStudio.service.consumer_producer;

import com.alibaba.fastjson.JSON;
import com.taotao.freeStudio.socket.constant.Constants;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;

public class IntegerConsumer implements Runnable{

    private IntegerStorage integerStorage;

    private WebSocketSession webSocketSession;

    public IntegerConsumer(IntegerStorage integerStorage, WebSocketSession webSocketSession) {
        this.integerStorage = integerStorage;
        this.webSocketSession = webSocketSession;
    }

    @Override
    public void run() {

        try {
            String jspSessionId = String.valueOf(webSocketSession.getAttributes().get(Constants.JSP_SESSION_ID));
            while(JSPRunningMap.getRunStatus(jspSessionId)){
                Integer pop = integerStorage.pop();
                webSocketSession.sendMessage(new TextMessage(JSON.toJSONString(pop)));
                Thread.sleep(5000);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
