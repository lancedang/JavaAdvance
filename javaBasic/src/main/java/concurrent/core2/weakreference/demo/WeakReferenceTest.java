package concurrent.core2.weakreference.demo;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.TimeUnit;

@Slf4j
//WeakReference 最简使用demo, 且验证一个对象若只有weakReference引用，
//被若引用WeakReference引用的形式: WeakReference weakReference = new WeakReference(xx)
//在gc的时候,那么xx就会被回收
public class WeakReferenceTest {

    public static void main(String[] args) throws InterruptedException {

        //new Apple() 单纯的new Apple()这个其实没有被任何引用，无强引用/无弱引用

        //apple变量代表强引用
        Apple apple = new Apple();
        //弱引用
        MyWeakReference reference = new MyWeakReference(apple);

        //括号里的new Apple()对象只被若引用指向
        MyWeakReference reference2 = new MyWeakReference(new Apple("apple2"));

        System.gc();

        TimeUnit.SECONDS.sleep(3);

        Thread apple1Thread = new Thread(new Runnable() {
            @Override
            public void run() {

                //每个线程循环各自检测 对应 的WeakReference get指向是否为null
                //为null说明已回收对应数据
                while (true) {
                    if (reference.get() == null) {
                        log.info("apple1 has 回收!!!");
                        break;
                    }else {
                        log.info("apple1 not 回收");
                        try {
                            TimeUnit.SECONDS.sleep(5);
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                    }
                }

            }
        });

        Thread apple2Thread = new Thread(new Runnable() {
            @Override
            public void run() {

                while (true) {
                    if (reference2.get() == null) {
                        log.info("apple2 has 回收");
                        break;
                    }else {
                        log.info("apple2 not 回收");
                        try {
                            TimeUnit.SECONDS.sleep(5);
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                    }
                }

            }
        });

        apple1Thread.start();
        apple2Thread.start();

    }
}
