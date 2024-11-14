package aqs;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class MyLockDemo {
    public static void main(String[] args) {

        MyLock myLock = new MyLock();

        Runnable runnable = () -> {
            myLock.lock();

            try {
                log.info(Thread.currentThread().getName() + " running task, please wait 10s");
                Thread.sleep(10 * 1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            myLock.unlock();
        };

        Thread thread1 = new Thread(runnable);
        Thread thread2 = new Thread(runnable);
        Thread thread3 = new Thread(runnable);

        thread1.start();
        thread2.start();
        thread3.start();


        log.info("main end");
    }
}
