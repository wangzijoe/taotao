package com.taotao.freeStudio.service.consumer_producer;

import org.apache.commons.lang3.StringUtils;

import java.util.LinkedList;
import java.util.List;

public class JSPRunningMap {

    private static List<JSPRunningStatus> runningMap = new LinkedList<>();


    public static void put(String jspSessionId, Boolean isRunning) {
        if (runningMap.size() > 2) {
            runningMap.remove(0);
        }
        runningMap.add(new JSPRunningStatus(jspSessionId, isRunning));
    }

    public static Boolean getRunStatus(String jspSessionId) {
        for (JSPRunningStatus status : runningMap) {
            if (StringUtils.equals(jspSessionId, status.getJspSessionId())) {
                return status.getIsRunning();
            }
        }
        return Boolean.FALSE;
    }
}
