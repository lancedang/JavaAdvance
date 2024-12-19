package concurrent.core2.aqs.locksupport;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.locks.LockSupport;
import java.util.concurrent.locks.ReentrantLock;

@Slf4j
public class CustomSynchronizer {
    private final ReentrantLock lock = new ReentrantLock();
    private volatile boolean isReady = false;
    private Thread parkedThread = null;

    public void prepare() {
        lock.lock();
        try {
            // 准备工作
            log.info("已准备好");
            isReady = true;
            // 唤醒等待的线程
            if (parkedThread != null) {
                log.info("唤醒中");
                LockSupport.unpark(parkedThread);
            }
        } finally {
            lock.unlock();
        }
    }

    public void waitForReady() {
        lock.lock();
        try {
            while (!isReady) {
                // 记录当前线程
                parkedThread = Thread.currentThread();
                // 阻塞当前线程，并提供 blocker 参数
                log.info("等待中。。。");
                LockSupport.park(this);
                // 清除 parkedThread
                parkedThread = null;
            }
            // 继续执行
            System.out.println(Thread.currentThread().getName() + " detected ready state, continuing execution...");
        } finally {
            lock.unlock();
        }
    }

    public static void main(String[] args) {
        CustomSynchronizer synchronizer = new CustomSynchronizer();

        Thread workerThread = new Thread(() -> {
            synchronizer.waitForReady();
            // 执行任务
            System.out.println(Thread.currentThread().getName() + " task executed.");
        }, "WorkerThread");

        workerThread.start();

        // 模拟准备工作
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        synchronizer.prepare();
    }
}