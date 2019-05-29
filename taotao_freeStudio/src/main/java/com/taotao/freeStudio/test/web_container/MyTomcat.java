package com.taotao.freeStudio.test.web_container;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

public class MyTomcat {


    private int port = 8080;

    private Map<String, String> urlServletMap = new HashMap<>();

    private MyTomcat(int port) {
        this.port = port;
    }

    private void initServletMapping() {
        for (MyServletMapping servletMapping : MyServletMappingConfig.servletMappingList) {
            urlServletMap.put(servletMapping.getUrl(), servletMapping.getClazz());
        }
    }

    private void dispatch(MyRequest myRequest, MyResponse myResponse) {
        String clazz = urlServletMap.get(myRequest.getUrl());

        try {
            Class<MyServlet> servlet = (Class<MyServlet>) Class.forName(clazz);
            MyServlet myServlet = servlet.newInstance();
            myServlet.service(myRequest, myResponse);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void start() {
        initServletMapping();

        ServerSocket serverSocket = null;
        try {
            serverSocket = new ServerSocket(port);
            while (true) {
                Socket accept = serverSocket.accept();
                InputStream inputStream = accept.getInputStream();

                OutputStream outputStream = accept.getOutputStream();

                MyRequest myRequest = new MyRequest(inputStream);
                MyResponse myResponse = new MyResponse(outputStream);
                dispatch(myRequest, myResponse);
                if (Integer.parseInt("1") < 0) return;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (null != serverSocket) {
                try {
                    serverSocket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void main(String[] args) {
        new MyTomcat(80).start();
    }
}
