package concurrent.core2.atom;

import concurrent.core2.weakreference.upper.A;

import java.util.concurrent.CountDownLatch;

public class XDemo {
    public static int  y = 0;
    public static void main(String[] args) throws InterruptedException {

        CountDownLatch latch = new CountDownLatch(10);

        for (int i = 0; i < 10; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    addByOne();
                    latch.countDown();
                }
            }).start();
        }

        latch.await();


        System.out.println(y);

    }

    public static void addByOne() {

        //所有多线程安全问题，都是thread1在执行到某个阶段时，发生线程切换，比如
        //此时thread2开始执行，thread2执行完成后
        //原来thread1所仰仗/依赖的环境/变量/上下文已被 thread2改变，但是thread1未感知到且基于非最新环境继续执行->错误

        //举例：x+1被thread1执行完，但还没有赋值给x
        //此刻，thread2又开始执行 x+1(此刻x还是1)=2,结果值2也没赋值给x
        //此刻，thread1又继续执行，此时x结果是2，接着thread2继续执行 x又被赋与2

        for (int i = 0; i < 1000; i++) {
            //9770 != 10000
            y = y + 1;
        }

    }
}
