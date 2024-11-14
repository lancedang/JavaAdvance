package concurrent.core2.weakreference.upper;

import concurrent.core2.ClockUtil;
import concurrent.core2.weakreference.mock.MyThread;
import concurrent.core2.weakreference.mock.MyThread2;
import concurrent.core2.weakreference.mock.MyThreadLocalDemo;
import concurrent.core2.weakreference.mock.ThreadLocalNoWeakReference;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

@Slf4j
public class CommonDemo {

    static CountDownLatch countDownLatch = new CountDownLatch(100);
    static CountDownLatch countDownLatch2 = new CountDownLatch(100);

    static AtomicInteger atomicInteger = new AtomicInteger(1);

    public static void main(String[] args) throws InterruptedException {
        ExecutorService executorService = Executors.newCachedThreadPool(new ThreadFactory() {
            @Override
            public Thread newThread(Runnable runnable) {
                //这个Runnable要传递给myThread,
                //要不只会创建线程，但是线程执行的run工作为null啊！！！
                MyThreadCommon myThread = new MyThreadCommon(runnable, "name" + atomicInteger.getAndIncrement());
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
                executorService.submit(CommonDemo::test);
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
    }

    public static void test() {
        try {
            //log.info("hi");
            //构造没有WeakReference的ThreadLocal
            Thread thread = Thread.currentThread();
            A threadLocalNoWeakReference = new A(thread.getName() + "-local");
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
}
