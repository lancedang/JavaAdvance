package concurrent.core2.aqs.clh;

import concurrent.core2.aqs.IncrementData;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicReference;


@Slf4j
public class CLHLockDemo {
    public static void main(String[] args) throws InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(20);
        int turn = 10 * 10000;
        int threads = 10;

        CLHLock lock = new CLHLock();

        CountDownLatch countDownLatch = new CountDownLatch(threads);

        for (int i = 0; i < threads; i++) {
            executorService.submit(() -> {

                try {
                    for (int j = 0; j < turn; j++) {
                        IncrementData.increment(lock);
                    }

                    countDownLatch.countDown();
                } catch (Exception e) {
                    log.error("error, ", e);
                }

            });
        }

        countDownLatch.await();

        log.info("sum={}", IncrementData.sum);


        AtomicReference<CLHLock.Node> tail = lock.getTail();
        //遍历下当前队列长度
        CLHLock.Node node = tail.get();
        int count = 0;
        while (node.getPreNode() != null) {
            node = node.getPreNode();
            count ++;
        }
        //unlock中preNode设为null的话，此处count=0
        //否则=100000，等于抢锁的次数
        log.info("count={}", count);
    }
}
