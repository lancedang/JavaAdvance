package concurrent.core2.cdlvscylic;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CyclicBarrierDemo {
    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(2);
        CyclicBarrier cyclicBarrier = new CyclicBarrier(2);

        for (int i = 0; i < 2; i++) {
            executorService.submit(new Runnable() {
                @Override
                public void run() {
                    System.out.println("开始等待");
                    try {
                        cyclicBarrier.await();
                        System.out.println("结束等待");
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    } catch (BrokenBarrierException e) {
                        throw new RuntimeException(e);
                    }
                }
            });


        }
    }
}
