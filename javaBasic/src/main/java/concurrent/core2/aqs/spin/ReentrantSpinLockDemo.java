package concurrent.core2.aqs.spin;

import concurrent.core2.aqs.IncrementData;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;


@Slf4j
public class ReentrantSpinLockDemo {
    public static void main(String[] args) throws InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        int threads = 10;
        int turns = 10*100;

        Lock lock = new ReentrantSpinLock();
        Task task = new Task(lock);

        CountDownLatch countDownLatch = new CountDownLatch(threads);

        for (int i = 0; i < threads; i++) {
            for (int i1 = 0; i1 < turns; i1++) {
                try {
                    executorService.submit(() -> IncrementData.increment(lock));
                } catch (Exception e) {
                    log.error("error, ", e);
                }
            }
            countDownLatch.countDown();
            log.info("cdl--");
        }

        countDownLatch.await();
        log.info("sum={}", IncrementData.sum);

    }

    public static class Task implements Runnable{

        Lock lock;

        int sum = 0;

        public Task(Lock lock) {
            this.lock = lock;
        }

        @Override
        public void run() {
            lock.lock();
            try {
                enter1();
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }

        }

        private void enter1() {
            lock.lock();
            try {
                log.info("enter enter1");
                enter2();
            } finally {
                lock.unlock();
                log.info("leave enter1");
            }
        }

        private void enter2() {
            lock.lock();
            try {
                log.info("enter 2");
            } finally {
                lock.unlock();
                log.info("leave enter2");
            }
        }
    }


}
