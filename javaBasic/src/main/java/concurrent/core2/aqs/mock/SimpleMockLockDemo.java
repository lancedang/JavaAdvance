package concurrent.core2.aqs.mock;

import concurrent.core2.aqs.IncrementData;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.Lock;

@Slf4j
public class SimpleMockLockDemo {
    public static void main(String[] args) throws InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(20);
        int turn = 10 * 10000;
        int threads = 10;

        CountDownLatch countDownLatch = new CountDownLatch(threads);
        Lock lock = new SimpleMockLock();

        //提交多个线程并发执行
        for (int j = 0; j < threads; j++) {
            executorService.submit(() -> {
                try {
                    //每个线程执行多次的互斥的++递增逻辑
                    for (int i = 0; i < turn; i++) {
                        IncrementData.increment(lock);
                    }
                    countDownLatch.countDown();
                    log.info("cdl count={}", countDownLatch.getCount());
                } catch (Exception e) {
                    log.error("exception, ", e);
                }
            });
        }
        countDownLatch.await();
        log.info("sum={}", IncrementData.sum);
    }
}
