package com.taotao.rest.consumer_producer;

/**
 * 仓库，用来存放产品
 *
 */
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

class Storage {

    private BlockingQueue<Product> queues = new LinkedBlockingQueue<>(10);

    /**
     * 生产
     *
     * @param p
     *            产品
     * @throws InterruptedException
     */
    void push(Product p) throws InterruptedException {
        queues.put(p);
    }

    /**
     * 消费
     *
     * @return 产品
     * @throws InterruptedException
     */
    Product pop() throws InterruptedException {
        return queues.take();
    }
}
