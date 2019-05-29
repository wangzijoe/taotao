package com.taotao.freeStudio.test.web_container;

public abstract class MyServlet {

    public static final String GET = "GET";

    public static final String POST = "POST";

    public abstract void doGet(MyRequest myRequest, MyResponse myResponse);

    public abstract void doPost(MyRequest myRequest, MyResponse myResponse);

    public void service(MyRequest myRequest, MyResponse myResponse) {
        if (null == myRequest) throw new IllegalArgumentException("Request is null");
        if (null == myResponse) throw new IllegalArgumentException("Response is null");
        if (myRequest.getMethod().equals(GET)) doGet(myRequest, myResponse);
        if (myRequest.getMethod().equals(POST)) doPost(myRequest, myResponse);
    }

}
