package concurrent.core2.atom;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicIntegerArray;

@Slf4j
public class AtomicArrayDemo {
    public static void main(String[] args) throws InterruptedException {

        ExecutorService executorService = Executors.newCachedThreadPool();
        ExecutorService safeExecutorService = Executors.newCachedThreadPool();

        int[] array = new int[]{0, 2, 3, 4, 5};
        int[] safeArray = new int[]{0, 2, 3, 4, 5};

        AtomicIntegerArray atomicIntegerArray = new AtomicIntegerArray(safeArray);

        CountDownLatch countDownLatch = new CountDownLatch(10);
        CountDownLatch safeCountDownLatch = new CountDownLatch(10);


        Runnable nonSafeRun = new Runnable() {
            @Override
            public void run() {
                //只有次数多的时候才能模拟出多线程并行不安全性
                for (int i = 0; i < 1000; i++) {
                    //模拟并行操作
                    int first = array[0];
                    array[0] = first + 1;
                }
                countDownLatch.countDown();
            }
        };

        for (int i = 0; i < 10; i++) {
            executorService.submit(nonSafeRun);
        }

        countDownLatch.await();
        log.info("array={}", array);

        Runnable safeRun = new Runnable() {
            @Override
            public void run() {

                //只有次数多的时候才能模拟出多线程并行不安全性
                for (int i = 0; i < 1000; i++) {
                    //通过AtomicIntegerArray在多线程环境下，线程安全的对数组某一个元素递增
                    atomicIntegerArray.getAndIncrement(0);
                }
                safeCountDownLatch.countDown();

            }
        };


        for (int i = 0; i < 10; i++) {
            safeExecutorService.submit(safeRun);
        }

        safeCountDownLatch.await();

        log.info("atomicIntegerArray={}", atomicIntegerArray);
    }
}
