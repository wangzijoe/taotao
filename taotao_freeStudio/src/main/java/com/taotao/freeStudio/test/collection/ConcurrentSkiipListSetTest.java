package com.taotao.freeStudio.test.collection;

import java.util.LinkedHashSet;
import java.util.Set;
import java.util.concurrent.ConcurrentSkipListSet;

public class ConcurrentSkiipListSetTest {

    public static void main(String[] args) {
        Set<String> sessions = new ConcurrentSkipListSet<>();

        sessions.add("G");
        sessions.add("F");
        sessions.add("A");
        sessions.add("Y");
        sessions.add("K");
        sessions.add("L");
        sessions.add("R");
        sessions.add("Z");
        sessions.add("X");

        System.out.println(sessions);

        sessions = new LinkedHashSet<>();

        sessions.add("G");
        sessions.add("F");
        sessions.add("A");
        sessions.add("Y");
        sessions.add("K");
        sessions.add("L");
        sessions.add("R");
        sessions.add("Z");
        sessions.add("X");

        System.out.println(sessions);

    }
}
