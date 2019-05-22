package com.taotao.freeStudio.test.java_transport;

import lombok.Data;

@Data
public class User {
    //定义对象属性
    private String name;
    private Integer age;
    //定义User类的无参构造
    public User() {
    }
    //定义User类的满参构造
    public User(String name, Integer age) {
        this.name = name;
        this.age = age;
    }

}
