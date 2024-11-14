package concurrent.core2.aqs.spin;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

@Slf4j
public class ReentrantSpinLock implements Lock {

    private AtomicReference<Thread> lockOwnerThread = new AtomicReference<>();

    /**
     * 为了实现可重入锁，我们需要引入一个计数器，用来记录一个重复获取锁的次数
     * 此变量为同一个线程在操作，没有必要加上volatile保障可见性和有序性
     */
    private int count = 0;

    @Override
    public void lock() {
        Thread thread = Thread.currentThread();
        //如果当前线程和lockOwnerThread一致，说明，
        //这个线程又来重复获取锁了
        if (thread == lockOwnerThread.get()) {
            //log.info("old count={},count={}", count,thread.getName());
            //count加1，记录当前线程重入次数
            count++;
            return;
        }

        while (!lockOwnerThread.compareAndSet(null, thread)) {
            try {
                Thread.sleep(0);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        //如果是首次获取锁，也要记录下次数
        count++;
        //log.info("thread={} lock success", thread.getName());
    }

    @Override
    public void unlock() {
        Thread thread = Thread.currentThread();
        //当前线程是锁的拥有者
        if (thread == lockOwnerThread.get()) {
            //如果当前线程只获取过一次锁，则将ownThread设为null
            if (count == 1) {
                lockOwnerThread.set(null);
            } else if (count > 1) {
                count--;
            }
        }
        //log.info("thread={} unlock success", thread.getName());
    }

    @Override
    public void lockInterruptibly() throws InterruptedException {

    }

    @Override
    public boolean tryLock() {
        return false;
    }

    @Override
    public boolean tryLock(long time, TimeUnit unit) throws InterruptedException {
        return false;
    }


    @Override
    public Condition newCondition() {
        return null;
    }
}
