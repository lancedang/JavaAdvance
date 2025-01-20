package advance.job.thread.producerconsumer;

import concurrent.core2.ClockUtil;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ArrayBlockingQueue;

@Slf4j
//使用BlockQueue模拟生产者消费者
public class BlockQueueDemo {
    public static void main(String[] args) {
        ArrayBlockingQueue<String> queue = new ArrayBlockingQueue<>(10);
        new Thread(() -> {
            while (true) {
                try {
                    String take = queue.take();
                    log.info("take item " + take);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();

        new Thread(() -> {
            while (true) {
                try {
                    String xx = "xx";
                    queue.put(xx);
                    ClockUtil.sleep(1);
                    log.info("add item " + xx);
                } catch (Exception e) {
                    log.error("error,", e);
                }
            }
        }).start();
    }
}
