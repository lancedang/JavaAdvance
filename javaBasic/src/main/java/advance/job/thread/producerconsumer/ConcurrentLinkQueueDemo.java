package advance.job.thread.producerconsumer;

import concurrent.core2.ClockUtil;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ConcurrentLinkedQueue;

@Slf4j
//非阻塞 线程安全 版本的消费者生产者模式
public class ConcurrentLinkQueueDemo {
    public static void main(String[] args) {
        ConcurrentLinkedQueue<String> queue = new ConcurrentLinkedQueue<>();
        new Thread(() -> {
            while (true) {
                String take = queue.poll();
                ClockUtil.sleep(2);
                log.info("take item {}", take);
            }
        }).start();

        new Thread(() -> {
            while (true) {
                String xx = "xx";
                //add插入失败会抛异常
                //queue.add(xx);
                //offer插入失败不会抛异常，直接返回false
                queue.offer(xx);
                ClockUtil.sleep(1);
                log.info("add item {}", xx);
            }
        }).start();
    }
}
