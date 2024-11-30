package concurrent.core2.block;

import concurrent.core2.ClockUtil;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

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
                    log.info(thread.getName() + " end take item " + take);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
        };

        Runnable putRun = new Runnable() {
            @Override
            public void run() {
                Thread thread = Thread.currentThread();
                String xx = "xx";
                try {
                    log.info(thread.getName() + " start add item " + thread.getName());
                    //put是SynchronizeQueue的阻塞插入操作，等待其他线程的take
                    queue.put(thread.getName());

                    //offer是非阻塞操作，如果队列满了，则返回false
                    //queue.offer("yy");
                    log.info(thread.getName() + " end add item " + thread.getName());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        };

        Runnable offerRun = new Runnable() {
            @Override
            public void run() {
                String e = "333";
                Thread thread = Thread.currentThread();
                log.info(thread.getName() + " offer start " + e);
                boolean offer = queue.offer(e);
                log.info(thread.getName() + " offer end result: " + offer);
            }
        };

        //put demo
        new Thread(putRun).start();
        //new Thread(putRun).start();
        ClockUtil.sleep(10);
        new Thread(takeRun).start();

        //offer demo
        ClockUtil.sleep(10);

        new Thread(offerRun).start();
        ClockUtil.sleep(1);

        new Thread(takeRun).start();
        ClockUtil.sleep(1);

        new Thread(offerRun).start();
        ClockUtil.sleep(1);

    }
}
