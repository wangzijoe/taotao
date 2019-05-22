package com.taotao.freeStudio.test.java_transport;

public class Demo1 {

    public static void main(String[] args) {
        //定义基本类型的变量并赋值
        int a = 2, b = 3 ;
        //调用方法,将a,b传递过去
        change(a, b);
        //a=2
        System.out.println("a="+a);
        //b=3
        System.out.println("b="+b);
    }


    //改变值的方法
    private static void change(int a, int b) {
        a = 5;
        b = 10;
        //方法中的a=5
        System.out.println("方法中的a="+a);
        //方法中的b=10
        System.out.println("方法中的b="+b);
    }


    /*
     * 方法中的a=5
     * 方法中的b=10
     * a=2
     * b=3
     * 基本类型的数据，作为参数传递的是值
     * 值传递在方法中改变参数值,不会影响到原来的数据,除非返回数据并将原来的数据覆盖
     * Process finished with exit code 0
     */
}
