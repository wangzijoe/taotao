package com.taotao.rest.consumer_producer;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.net.URL;

/**
 * 生产者
 */
public class Producer implements Runnable {

    private String name;
    private Storage storage;
    private File logFile;

    Producer(String name, Storage s, File logFile) {
        this.name = name;
        this.storage = s;
        this.logFile = logFile;
    }

    @SuppressWarnings("InfiniteLoopStatement")
    public void run() {
        try {
            while (true) {
                Product product = new Product((int) (Math.random() * 10000)); // 产生0~9999随机整数
                storage.push(product);
                String logLine = name + " 已生产(" + product.toString() + ")\n\r";
                FileUtils.writeStringToFile(logFile, logLine, Boolean.TRUE);
                Thread.sleep(10000);
            }
        } catch (Exception e1) {
            e1.printStackTrace();
        }

    }
}
