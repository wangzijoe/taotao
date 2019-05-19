package com.taotao.freeStudio.service.consumer_producer;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;

public class IntegerStorage {

    private BlockingQueue<Integer> queues = new LinkedBlockingDeque<>(5);

    /**
     * 生产
     *
     * @param p
     *            产品
     * @throws InterruptedException
     */
    void push(Integer p) throws InterruptedException {
        queues.put(p);
    }

    /**
     * 消费
     *
     * @return 产品
     * @throws InterruptedException
     */
    Integer pop() throws InterruptedException {
        return queues.take();
    }
}
