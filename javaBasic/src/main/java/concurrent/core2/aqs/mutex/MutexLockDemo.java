package concurrent.core2.aqs.mutex;

import concurrent.core2.aqs.IncrementData;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Slf4j
public class MutexLockDemo {
    public static void main(String[] args) throws InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(20);
        int turn = 10 * 10000;
        int threads = 10;

        MutexLock lock = new MutexLock();

        CountDownLatch countDownLatch = new CountDownLatch(threads);

        for (int i = 0; i < threads; i++) {
            executorService.submit(() -> {

                try {
                    for (int j = 0; j < turn; j++) {
                        IncrementData.increment(lock);
                    }

                    countDownLatch.countDown();
                    log.info("cdl--");
                } catch (Exception e) {
                    log.error("error, ", e);
                }

            });
        }

        countDownLatch.await();

        log.info("sum={}", IncrementData.sum);
    }
}
