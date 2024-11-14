package concurrent.core2.atom;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.LongAdder;

@Data
@Slf4j
public class LongAdderDemo {
    public static void main(String[] args) throws InterruptedException {
        ExecutorService safeExecutorService = Executors.newCachedThreadPool();
        ExecutorService unsafeExecutorService = Executors.newCachedThreadPool();
        ExecutorService innerExecutorService = Executors.newCachedThreadPool();

        CountDownLatch countDownLatch = new CountDownLatch(10);

        LongAdder longAdder = new LongAdder();

        Runnable unsafeRun = new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 100000; i++) {
                    //操作也很简单
                    longAdder.add(1);
                }
                countDownLatch.countDown();
            }
        };

        for (int i = 0; i < 10; i++) {
            innerExecutorService.submit(unsafeRun);
        }

        countDownLatch.await();

        log.info("total={}", longAdder.intValue());

    }
}
