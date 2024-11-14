package concurrent.core2.workermaster;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 每个Worker其实就是一个线程（线程体也行）
 */

@Data
@Slf4j
public class Worker<T extends Task> {

    //所有worker对象的id,故static
    private static AtomicInteger workerIdAdder = new AtomicInteger(1);

    //每个Worker有自己的一亩三分地，即阻塞队列，
    //worker从自己的一亩三分地里面不停地(while true)take 任务，然后执行
    private LinkedBlockingQueue<T> workerTaskQueue = new LinkedBlockingQueue<>();

    //异步执行器，执行从一亩三分地里取数据并执行的动作
    private Thread executor;

    private int workId;

    public Worker() {
        workId = workerIdAdder.getAndIncrement();
        //这里通过一个线程来执行task execute的过程
        //其实也可以用线程池啥的，为何非得用异步线程，思考下
        //如果不在构造函数中启动线程，也得提供一个对外的启动入口
        //且启动后不能阻塞调用者caller执行
        //所以就用一个小线程完成吧，哈哈哈
        executor = new Thread(() -> dealTask());
        //一直启动了，线程池
        executor.start();
    }

    //增加一个往阻塞队列添加task的方法
    public void submit(T task) {
        try {
            workerTaskQueue.put(task);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    //从阻塞队列取数据并执行，私有的就ok了
    private void dealTask() {
        //while true一直循环不停
        for (; ; ) {
            try {
                T task = workerTaskQueue.take();
                task.execute();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
