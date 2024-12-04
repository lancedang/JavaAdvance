package waitnotify;

import lombok.extern.slf4j.Slf4j;

import javax.swing.*;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

@Slf4j
public class WaitNotifyDemo {
    public static void main(String[] args) throws InterruptedException {
        ReentrantLock lock = new ReentrantLock();

        Object mutex = new Object();

        Thread t1 = new Thread(() -> {

            synchronized (mutex) {
                try {
                    System.out.println("thread1 start");
                    Thread.sleep(5000);
                    mutex.wait();
                    System.out.println("thread1 end");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

        });

        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (mutex) {
                    try {
                        System.out.println(Thread.currentThread().getName() + " notify start, now=" + System.currentTimeMillis());
                        mutex.notify();
                        System.out.println(Thread.currentThread().getName() + " end notify");
                    } catch (Exception e) {

                    }

                }
            }
        });

        t1.start();
        TimeUnit.SECONDS.sleep(2);

        t2.start();

    }


}
