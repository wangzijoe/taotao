package com.taotao.freeStudio.service.consumer_producer;

import lombok.Data;

@Data
public class JSPRunningStatus {


    private String jspSessionId;

    private Boolean isRunning;

    public JSPRunningStatus(String jspSessionId, Boolean isRunning) {
        this.jspSessionId = jspSessionId;
        this.isRunning = isRunning;
    }
}
