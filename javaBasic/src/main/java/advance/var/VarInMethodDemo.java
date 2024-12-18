package advance.var;

import lombok.Data;

public class VarInMethodDemo {

    static void change(A a) {
        a.name = "wangwu";
    }

    public static void main(String[] args) {
        A a = new A();
        a.name = "lisi";

        //Java没有golang指针的概念
        //Java都是值传递：
        // 基本数据类型：int string，这些都是副本传递，修改不影响原始值
        // 引用类型：Array List，都是引用传递，改变引用指向的对象，影响原来的值
        //注：这里对比golang, golang直接传递a struct是不影响原值的，必须传递 &a这种指针格式才能修改原值
        change(a);

        System.out.println(a.name);
    }

    @Data
    static class A {
        private String name;
    }
}
