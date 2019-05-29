package com.taotao.freeStudio.test.web_container;

import lombok.Data;

@Data
public class MyServletMapping {

    private String servletName;

    private String url;

    private String clazz;

    public MyServletMapping(String servletName, String url, String clazz) {
        this.servletName = servletName;
        this.url = url;
        this.clazz = clazz;
    }
}
