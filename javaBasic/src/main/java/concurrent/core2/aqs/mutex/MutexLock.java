package concurrent.core2.aqs.mutex;

import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.LockSupport;

public class MutexLock implements Lock {

    private volatile Thread ownerThread;
    private AtomicInteger state = new AtomicInteger(0);
    private ConcurrentLinkedQueue<Thread> waitQueue = new ConcurrentLinkedQueue<>();


    @Override
    public void lock() {
        Thread thread = Thread.currentThread();

        if (ownerThread != null && (ownerThread == Thread.currentThread())) {
            return;
        }

/*        while (!state.compareAndSet(0, 1)) {
            //这样写 add多少次呢，不对
            waitQueue.add(thread);
        }*/

        try {
            waitQueue.add(thread);
/*            while (!state.compareAndSet(0, 1)) {
                //如果没抢到，休眠一下继续尝试（继续while循环）
                LockSupport.park();
            }
            ownerThread = thread;*/
            while (!state.compareAndSet(0, 1)) {
                //这里是有park()和使用Thread.sleep()是一样的，
                //在success为false（即没有获取到锁）的时候，让当前线程先休眠下在继续尝试抢锁
                LockSupport.park();
            }
            ownerThread = thread;
        } finally {
            //如果cas成功直接进入finally，退出等待队列
            waitQueue.remove(thread);
        }
    }

    @Override
    public void unlock() {
/*        //这个逻辑不能执行，否则throw这个异常，说明，demo在unlock的时候，ownerThread可能为null
        //判断当前锁是否被占用
        if (ownerThread == null) {
            throw new IllegalMonitorStateException("锁未被占用");
        }*/

        Thread thread = Thread.currentThread();
        if (ownerThread != null && thread != ownerThread) {
            throw new IllegalMonitorStateException("当下线程尚未获取锁");
        }

        //这个地方必须这么写while true
        while (true) {
            boolean b = state.compareAndSet(1, 0);
            if (b) {
                break;
            }

        }
        ownerThread = null;
        if (!waitQueue.isEmpty()) {
            for (Thread thread1 : waitQueue) {
                LockSupport.unpark(thread1);
            }
        }
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
