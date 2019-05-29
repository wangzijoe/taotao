package com.taotao.freeStudio.test.web_container;

import java.io.IOException;

public class FindGirlServlet extends MyServlet {
    @Override
    public void doGet(MyRequest myRequest, MyResponse myResponse) {
        try {
            myResponse.write("get girls....");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void doPost(MyRequest myRequest, MyResponse myResponse) {
        try {
            myResponse.write("post girls....");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
