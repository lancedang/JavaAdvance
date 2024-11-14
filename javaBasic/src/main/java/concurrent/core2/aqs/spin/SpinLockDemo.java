package concurrent.core2.aqs.spin;

import concurrent.core2.aqs.IncrementData;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Slf4j
public class SpinLockDemo {
    public static void main(String[] args) throws InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(20);
        int turns = 10000;
        int threads = 10;

        SpinLock lock = new SpinLock();
        CountDownLatch countDownLatch = new CountDownLatch(threads);

        for (int i = 0; i < threads; i++) {
            executorService.submit(new Runnable() {
                @Override
                public void run() {

                    for (int j = 0; j < turns; j++) {
                        try {
                            IncrementData.increment(lock);
                        } catch (Exception e) {
                            log.error("error, ", e);
                        }
                    }

                    countDownLatch.countDown();
                }
            });
        }

        countDownLatch.await();
        log.info("sum={}", IncrementData.sum);
    }
}
