package com.taotao.freeStudio.test.web_container;

import java.io.IOException;
import java.io.OutputStream;

public class MyResponse {

    private OutputStream outputStream;

    public MyResponse(OutputStream outputStream) {
        this.outputStream = outputStream;
    }

    public void write(String content) throws IOException {
        String httpResponse =
                "HTTP/1.1 200 OK\n" +
                "Content-Type: text/html\n" +
                "\r\n" + content;
        outputStream.write(httpResponse.getBytes());
        outputStream.close();
    }
}
