package com.taotao.freeStudio.test.web_container;

import lombok.Data;

import java.io.IOException;
import java.io.InputStream;

@Data
public class MyRequest {

    private String url;

    private String method;

    public MyRequest(InputStream inputStream) throws IOException {
        String httpRequestStr = "";
        byte[] httpRequestStrBytes = new byte[1024];
        int length;
        if ((length = inputStream.read(httpRequestStrBytes)) > 0) {
            httpRequestStr = new String(httpRequestStrBytes, 0, length);
        }

        String httpHead = httpRequestStr.split("\n")[0];
        method = httpHead.split("\\s")[0];
        url = httpHead.split("\\s")[1];

        System.out.println(this);
    }
}
