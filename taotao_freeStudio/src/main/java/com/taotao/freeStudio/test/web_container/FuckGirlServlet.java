package com.taotao.freeStudio.test.web_container;

import java.io.IOException;

public class FuckGirlServlet extends MyServlet {
    @Override
    public void doGet(MyRequest myRequest, MyResponse myResponse) {
        try {
            myResponse.write("get girls....fuck");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void doPost(MyRequest myRequest, MyResponse myResponse) {
        try {
            myResponse.write("get girls....fuck");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
