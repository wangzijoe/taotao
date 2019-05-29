package com.taotao.freeStudio.test.web_container;

import java.util.ArrayList;
import java.util.List;

public class MyServletMappingConfig {

    public static List<MyServletMapping> servletMappingList = new ArrayList<>();

    static {
        servletMappingList.add(new MyServletMapping("findGirl", "/find","com.taotao.freeStudio.test.web_container.FindGirlServlet"));
        servletMappingList.add(new MyServletMapping("fuckGirl", "/fuck","com.taotao.freeStudio.test.web_container.FuckGirlServlet"));
    }
}
