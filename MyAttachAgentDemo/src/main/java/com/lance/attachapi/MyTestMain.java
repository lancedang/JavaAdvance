package com.lance.attachapi;

import java.util.concurrent.TimeUnit;

//目的：while true中通过 attachAPI 改变foo()方法的返回值
public class MyTestMain {
    public static void main(String[] args) throws InterruptedException {
        while (true) {
            int foo = foo();
            System.out.println(foo);

            TimeUnit.SECONDS.sleep(2);
        }
    }

    public static int foo() {
        return 100;
    }
}
