package concurrent.core2.workermaster;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * 一组worker集合，工头而已，让下面的工人干活,master也是一个线程体
 */
@Data
@Slf4j
public class MasterWorker<T extends Task> {

    //存放/记录所有的worker，因为只有一个master，所以不需使用concurrent map
    private Map<String, Worker<T>> workerMap = new HashMap<>();

    //master自己的阻塞队列，
    //相当于client提交task->master的block queue->master的thread while true 从queue取再提交到worker的queue
    //worker的thread while true从queue取并执行之
    private LinkedBlockingQueue<T> masterTaskQueue = new LinkedBlockingQueue<>();

    //master也附属一个thread来while true take数据
    private Thread masterExecutor;

    public MasterWorker(int workerSize) {

        //包工头构造几个工人
        for (int i = 0; i < workerSize; i++) {
            Worker<T> worker = new Worker<>();
            workerMap.put("worker-" + i, worker);
        }

        //master也不断take queue
        masterExecutor = new Thread(() -> run());
        masterExecutor.start();

    }

    //提供对外的add task接口
    public void submit(T task) {
        try {
            //简单使用阻塞式添加任务
            masterTaskQueue.put(task);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void run() {
        //死循环
        for (; ; ) {
            //注意这个遍历顺序，先循环所有worker，让其轮流从queue中取数据
            //轮训每个worker来执行task
            for (Map.Entry<String, Worker<T>> entry : workerMap.entrySet()) {
                try {
                    T task = masterTaskQueue.take();
                    Worker<T> worker = entry.getValue();
                    worker.submit(task);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
