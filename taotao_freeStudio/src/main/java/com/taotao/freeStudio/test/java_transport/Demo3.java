package com.taotao.freeStudio.test.java_transport;

public class Demo3 {

    public static void main(String[] args) {
        //创建一个User类对象,并赋值
        User user = new User("提莫",20);
        //调用方法前 User{name='提莫', age=20}
        System.out.println(user);
        //调用方法
        change(user);
        //调用方法后 User{name='德玛', age=18}
        System.out.println(user);
    }
    //改变值的方法
    private static void change(User u) {
        //改变值
        u.setName("德玛");
        u.setAge(18);
        //方法中的u uUser{name='德玛', age=18}
        System.out.println("u"+u);
    }

    /*
     * User(name=提莫, age=20)
     * uUser(name=德玛, age=18)
     * User(name=德玛, age=18)
     *
     * 同理对象作为参数传递的也是对象的地址值,在方法中是对 对象地址中的数据修改,数据发生改变
     *
     * Process finished with exit code 0
     */
}
