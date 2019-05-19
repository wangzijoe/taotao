package com.taotao.freeStudio.controller.consumer_producer;

import com.alibaba.fastjson.JSON;
import com.taotao.common.pojo.TaotaoResult;
import com.taotao.freeStudio.service.consumer_producer.IntegerConsumer;
import com.taotao.freeStudio.service.consumer_producer.IntegerProducer;
import com.taotao.freeStudio.service.consumer_producer.IntegerStorage;
import com.taotao.freeStudio.service.consumer_producer.JSPRunningMap;
import com.taotao.freeStudio.socket.constant.Constants;
import com.taotao.freeStudio.socket.handler.TWebSocketHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.socket.WebSocketSession;

import java.util.List;
import java.util.Objects;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Controller
@RequestMapping("consumer_producer")
public class ConsumerProducerController {

    @Autowired
    private TWebSocketHandler tWebSocketHandler;

    @ResponseBody
    @RequestMapping(value = "buildTable",method=RequestMethod.GET)
    public String buildTable(String callback,String jspSessionId) {
        WebSocketSession socketSession = null;
        List<WebSocketSession> sessions = tWebSocketHandler.getSESSIONS();
        for (WebSocketSession s : sessions) {
            if (jspSessionId.equals(s.getAttributes().get(Constants.JSP_SESSION_ID))) {
                socketSession = s;
            }
        }
        if (null != socketSession) {
            JSPRunningMap.put(jspSessionId, true);
            IntegerStorage integerStorage = new IntegerStorage();
            ExecutorService executorService = Executors.newCachedThreadPool();
            IntegerProducer integerProducer = new IntegerProducer(integerStorage, socketSession);
            IntegerConsumer integerConsumer = new IntegerConsumer(integerStorage, socketSession);
            executorService.submit(integerProducer);
            executorService.submit(integerConsumer);
        }

        return callback +"("+JSON.toJSONString(TaotaoResult.ok()) +");";
    }


    @ResponseBody
    @RequestMapping("closeProducer")
    public String closeProducer(String callback,String jspSessionId) {
        JSPRunningMap.put(jspSessionId, false);
        return callback +"("+JSON.toJSONString(TaotaoResult.ok()) +");";
    }
}
