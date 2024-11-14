package concurrent.core2.weakreference.mock;

import concurrent.core2.ClockUtil;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

@Data
@Slf4j
public class MyThreadLocalDemo {
    static CountDownLatch countDownLatch = new CountDownLatch(100);
    static CountDownLatch countDownLatch2 = new CountDownLatch(100);

    static AtomicInteger atomicInteger = new AtomicInteger(1);

    public static void main(String[] args) throws InterruptedException {
        ExecutorService executorService = Executors.newCachedThreadPool( new ThreadFactory() {
            @Override
            public Thread newThread(Runnable runnable) {
                //这个Runnable要传递给myThread,
                //要不只会创建线程，但是线程执行的run工作为null啊！！！
                MyThread myThread = new MyThread( runnable, "name" + atomicInteger.getAndIncrement());
                return myThread;
            }
        });

        ExecutorService referenceExecutor = Executors.newCachedThreadPool(new ThreadFactory() {
            @Override
            public Thread newThread(Runnable runnable) {
                MyThread2 myThread = new MyThread2(runnable, "name" + atomicInteger.getAndIncrement());
                return myThread;
            }
        });

        log.info("test1 begin");

        for (int i = 0; i < 100; i++) {
            try {
                executorService.submit(MyThreadLocalDemo::test);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        countDownLatch.await();
        log.info("test1 submit finish");
        log.info("test1 before gc");

        System.gc();

        log.info("test1 after gc");


        ClockUtil.sleep(10);

        log.info("see test entry map内容");

        log.info("test2 begin");
        for (int i = 0; i < 100; i++) {
            try {
                referenceExecutor.submit(MyThreadLocalDemo::test2);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        countDownLatch2.await();

        log.info("test2 submit finish");

        log.info("test2 before gc");

        System.gc();

        log.info("test2 after gc");
        ClockUtil.sleep(10);


    }



    public static void test() {
        try {
            //log.info("hi");
            //构造没有WeakReference的ThreadLocal
            Thread thread = Thread.currentThread();

            //这个方法体被线程池多次执行
            //每次都会创建ThreadLocal实例-局部变量
            //原则上，一个线程执行结束后，该方法体内的局部变量，应该被回收
            //threadLocalNoWeakReference 强引用消失
            ThreadLocalNoWeakReference threadLocalNoWeakReference = new ThreadLocalNoWeakReference(thread.getName() + "-local");
            threadLocalNoWeakReference.set("xxx");
            threadLocalNoWeakReference.get();

        } catch (Exception e) {
            e.printStackTrace();
            log.info("error");
        } finally {
            //log.info("finally");
            countDownLatch.countDown();
        }
    }

    public static void test2() {
        try {
            //log.info("hi");
            //构造 有WeakReference的ThreadLocal
            Thread thread = Thread.currentThread();
            ThreadLocalWithWeakReference myThreadLocal =
                    new ThreadLocalWithWeakReference(thread.getName() + "-refer local");
            myThreadLocal.set("refer test");
            myThreadLocal.get();

        } catch (Exception e) {
            e.printStackTrace();
            log.info("error");
        } finally {
            //log.info("finally");
            countDownLatch2.countDown();
        }
    }
}
