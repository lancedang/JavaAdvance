package concurrent.core2.block;

import concurrent.core2.ClockUtil;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.time.Clock;
import java.util.concurrent.SynchronousQueue;

@Data
@Slf4j
public class SynchronousQueueDemo {
    public static void main(String[] args) {
        SynchronousQueue<String> queue = new SynchronousQueue<>();

        Runnable takeRun = new Runnable() {
            @Override
            public void run() {
                Thread thread = Thread.currentThread();
                try {
                    log.info(thread.getName() + " start take item");
                    //take是阻塞操作，等待其他线程的insert动作
                    String take = queue.take();
                    log.info(thread.getName() + " end take item");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
        };

        Runnable putRun = new Runnable() {
            @Override
            public void run() {
                Thread thread = Thread.currentThread();
                try {
                    log.info(thread.getName() + " start add item");
                    //put是SynchronizeQueue的阻塞插入操作，等待其他线程的take
                    queue.put("xx");
                    log.info(thread.getName() + " end add item");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        };


        new Thread(putRun).start();
        new Thread(putRun).start();
        ClockUtil.sleep(10);
        new Thread(takeRun).start();

    }
}
