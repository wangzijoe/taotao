package com.taotao.freeStudio.test.java_transport;

public class Demo2 {

    public static void main(String[] args) {
        //定义数组类型的变量并赋值
        int [] a  ={3};
        //调用方法,将数组作为参数传递
        change(a);
        //a[0]=5
        System.out.println("a[0]="+a[0]);
    }
    //改变值的方法
    private static void change(int [] a) {
        a[0] = 5;
        //方法中的a[0]=5
        System.out.println("方法中的a[0]="+a[0]);
    }

    /*
     * 方法中的a[0]=5
     * a[0]=5
     *
     * 数组和集合类型数据传递的是地址值
     * 数组调用方法传递过去的是数组的地址,在方法中相当于直接去数组所在的地方修改数组中的数据,所以发现数组中的值改变了
     * Process finished with exit code 0
     */
}
