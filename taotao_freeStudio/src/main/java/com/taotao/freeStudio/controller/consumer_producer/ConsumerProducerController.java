package com.taotao.freeStudio.controller.consumer_producer;

import com.alibaba.fastjson.JSON;
import com.taotao.common.pojo.TaotaoResult;
import com.taotao.freeStudio.service.consumer_producer.IntegerConsumer;
import com.taotao.freeStudio.service.consumer_producer.IntegerProducer;
import com.taotao.freeStudio.service.consumer_producer.IntegerStorage;
import com.taotao.freeStudio.socket.handler.TWebSocketHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Controller
@RequestMapping("consumer_producer")
public class ConsumerProducerController {

    @Autowired
    private TWebSocketHandler tWebSocketHandler;

    @ResponseBody
    @RequestMapping(value = "buildTable", method = RequestMethod.GET)
    public String buildTable(String callback, String jspSessionId) {

        IntegerStorage integerStorage = new IntegerStorage();
        ExecutorService executorService = Executors.newCachedThreadPool();
        IntegerProducer integerProducer = new IntegerProducer(jspSessionId, integerStorage, tWebSocketHandler);
        IntegerConsumer integerConsumer = new IntegerConsumer(jspSessionId, integerStorage, tWebSocketHandler);
        executorService.submit(integerProducer);
        executorService.submit(integerConsumer);

        return callback + "(" + JSON.toJSONString(TaotaoResult.ok()) + ");";
    }

}
