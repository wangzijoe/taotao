package com.taotao.rest.consumer_producer;

/**
 * 产品
 */
public class Product {

    private int id;

    Product(int id) {
        this.id = id;
    }

    // 重写toString方法
    public String toString() {
        return "产品：" + this.id;
    }
}
