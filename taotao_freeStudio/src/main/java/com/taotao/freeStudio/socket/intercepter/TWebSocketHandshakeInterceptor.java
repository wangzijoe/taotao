package com.taotao.freeStudio.socket.intercepter;

import com.taotao.freeStudio.socket.constant.Constants;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.server.HandshakeInterceptor;
import org.springframework.web.socket.server.support.HttpSessionHandshakeInterceptor;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;


public class TWebSocketHandshakeInterceptor extends HttpSessionHandshakeInterceptor implements HandshakeInterceptor {


    //握手前
    public boolean beforeHandshake(ServerHttpRequest request,
                                   ServerHttpResponse response, WebSocketHandler wsHandler,
                                   Map<String, Object> attributes) throws Exception {

        /*
         * 获取请求参数，首先我们要获取HttpServletRequest对象才能获取请求参数；
         * 当ServerHttpRequset的层次结构打开后其子类可以获取到我们想要的http对象，那么就简单了。
         * 我这里是把获取的请求数据绑定到session的map对象中（attributes）
         */
        HttpServletRequest servletRequest = ((ServletServerHttpRequest) request).getServletRequest();
        String id = servletRequest.getSession().getId();
        System.out.println("beforeHandshake: \n" + id);
        String jspSessionId = servletRequest.getParameter(Constants.JSP_SESSION_ID);
        attributes.put(Constants.JSP_SESSION_ID, jspSessionId);
        return super.beforeHandshake(request, response, wsHandler, attributes);
    }

    //握手后
    public void afterHandshake(ServerHttpRequest request, ServerHttpResponse response,
                               WebSocketHandler wsHandler, Exception e) {
        super.afterHandshake(request, response, wsHandler, e);
    }
}
