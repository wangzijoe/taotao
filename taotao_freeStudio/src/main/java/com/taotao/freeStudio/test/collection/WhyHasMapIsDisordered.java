package com.taotao.freeStudio.test.collection;

import java.util.HashMap;
import java.util.Map;

public class WhyHasMapIsDisordered {


    public static void main(String[] args) {
        Map<String, Integer> m = new HashMap<>();
        for (int i = 0; i < 10; i++) {
            m.put("key" + i, i);

            System.out.println(m);

        }
    }
}
