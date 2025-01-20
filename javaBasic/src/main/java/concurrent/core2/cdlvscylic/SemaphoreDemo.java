package concurrent.core2.cdlvscylic;

import concurrent.core2.ClockUtil;
import lombok.extern.slf4j.Slf4j;

import java.time.Clock;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

@Slf4j
public class SemaphoreDemo {
    public static void main(String[] args) {
        Semaphore semaphore = new Semaphore(2);
        ExecutorService executorService = Executors.newCachedThreadPool();

        for (int i = 0; i < 20; i++) {
            executorService.execute(() -> {
                Thread thread = Thread.currentThread();
                try {
                    log.info("请求-干活-{}", thread.getName());
                    semaphore.acquire();
                    //相对ReentrantLock, Semaphore 同一时刻允许有n个线程同时执行/获取资源，
                    // 其余的大于n的线程会被阻塞，直到获取到资源被释放后才可以执行
                    log.info("真正-干活-{}", thread.getName());
                    ClockUtil.sleep(10);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                } finally {
                    log.info("结束-干活-{}", thread.getName());
                    semaphore.release();
                }
            });
        }

    }
}
