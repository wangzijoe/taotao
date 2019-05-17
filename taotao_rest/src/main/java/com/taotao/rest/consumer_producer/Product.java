package com.taotao.rest.consumer_producer;
/**
 * 产品
 *
 */
public class Product {

    private int id;

    public Product(int id) {
        this.id = id;
    }

    public String toString() {// 重写toString方法
        return "产品：" + this.id;
    }
}
