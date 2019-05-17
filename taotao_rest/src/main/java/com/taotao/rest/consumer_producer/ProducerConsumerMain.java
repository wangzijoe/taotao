package com.taotao.rest.consumer_producer;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 对于多线程程序来说，不管任何编程语言，生产者和消费者模型都是最经典的。就像学习每一门编程语言一样，Hello World！都是最经典的例子。
 *
 * 实际上，准确说应该是“生产者-消费者-仓储”模型，离开了仓储，生产者消费者模型就显得没有说服力了。
 * 对于此模型，应该明确一下几点：
 * 1、生产者仅仅在仓储未满时候生产，仓满则停止生产。
 * 2、消费者仅仅在仓储有产品时候才能消费，仓空则等待。
 * 3、当消费者发现仓储没产品可消费时候会通知生产者生产。
 * 4、生产者在生产出可消费产品时候，应该通知等待的消费者去消费。
 *                    .::::.
 *                  .::::::::.
 *                 :::::::::::  FUCK YOU
 *             ..:::::::::::'
 *           '::::::::::::'
 *             .::::::::::
 *        '::::::::::::::..
 *             ..::::::::::::.
 *           ``::::::::::::::::
 *            ::::``:::::::::'        .:::.
 *           ::::'   ':::::'       .::::::::.
 *         .::::'      ::::     .:::::::'::::.
 *        .:::'       :::::  .:::::::::' ':::::.
 *       .::'        :::::.:::::::::'      ':::::.
 *      .::'         ::::::::::::::'         ``::::.
 *  ...:::           ::::::::::::'              ``::.
 * ```` ':.          ':::::::::'                  ::::..
 *                    '.:::::'                    ':'````..
 */
public class ProducerConsumerMain {


    public static void main(String[] args) {

        Storage s = new Storage();

        ExecutorService service = Executors.newCachedThreadPool();
        Producer p1 = new Producer("张三", s);
        Producer p2 = new Producer("李四", s);
        Consumer c1 = new Consumer("王五", s);
        Consumer c2 = new Consumer("老刘", s);
        Consumer c3 = new Consumer("老林", s);
        service.submit(p1);
        service.submit(p2);
        service.submit(c1);
        service.submit(c2);
        service.submit(c3);

    }

}
