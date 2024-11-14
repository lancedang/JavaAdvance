package concurrent.core2.aqs.mutex;

import concurrent.core2.ClockUtil;
import concurrent.core2.aqs.IncrementData;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Slf4j
public class ShareMutexLockDemo {
    public static void main(String[] args) throws InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(20);
        int turn = 10 * 1000;
        int threads = 10;

        ShareMutexLock lock = new ShareMutexLock(2);

        CountDownLatch countDownLatch = new CountDownLatch(threads);

        for (int i = 0; i < threads; i++) {
            executorService.submit(() -> {

                try {
                    for (int j = 0; j < turn; j++) {
                        lock.lock();

                        try {
                            //这里通过long耗时 互斥资源，体现同时刻抢占锁的线程都有哪些/个数几个来进行验证
                            log.info("start long time task");
                            ClockUtil.sleep(0);
                            log.info("end long time task");
                        } finally {
                            lock.unlock();
                        }

                    }

                    countDownLatch.countDown();
                    log.info("wait queue size={}", lock.getWaitQueue().size());
                    log.info("cdl--");
                } catch (Exception e) {
                    log.error("error, ", e);
                }

            });
        }

        countDownLatch.await();

        log.info("wait queue size={}", lock.getWaitQueue().size());

        log.info("sum={}", IncrementData.sum);
    }
}
