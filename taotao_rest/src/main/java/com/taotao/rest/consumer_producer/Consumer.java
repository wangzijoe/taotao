package com.taotao.rest.consumer_producer;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.net.URL;

/**
 * 消费者
 */
public class Consumer implements Runnable {

    private String name;
    private Storage storage;
    private File LogFile;

    Consumer(String name, Storage s, File LogFile) {
        this.name = name;
        this.storage = s;
        this.LogFile = LogFile;
    }

    @SuppressWarnings("InfiniteLoopStatement")
    public void run() {
        try {
            while (true) {

                Product product = storage.pop();
                String logLine = "$" + name + " 已消费(" + product.toString() + ")\n\r";
                FileUtils.writeStringToFile(LogFile, logLine, Boolean.TRUE);
                Thread.sleep(10000);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
