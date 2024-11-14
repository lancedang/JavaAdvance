package concurrent.core2.aqs.mutex;

import com.google.common.collect.Sets;
import concurrent.core2.ClockUtil;
import lombok.extern.slf4j.Slf4j;

import java.util.Set;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.LockSupport;

@Slf4j
public class ShareMutexLock implements Lock {

    private volatile Set<Thread> ownerThreads = Sets.newConcurrentHashSet();
    private final ConcurrentLinkedQueue<Thread> waitQueue = new ConcurrentLinkedQueue<>();
    private final AtomicInteger state;

    public ConcurrentLinkedQueue<Thread> getWaitQueue() {
        return waitQueue;
    }

    public ShareMutexLock(int shareNumber) {
        //state数量标志着锁可以被同时占用的线程个数
        //标志可以分配给线程的剩余名额
        this.state = new AtomicInteger(shareNumber);
    }

    @Override
    public void lock() {
        Thread thread = Thread.currentThread();
        //1.判断当前线程是否已经在ownerThreads里了
        if (ownerThreads.contains(thread)) {
            //该线程已经获取过锁了，直接退出，从而进入互斥代码
            return;
        }

        //2.如果没有包含在ownThreads共享集合里，开始尝试进入
        //不论名额剩余量，只要抢锁，先放入等待队列，若state剩余值ok,则从等待队列退出,否则一直在剩余队列里呆着吧您
        waitQueue.add(thread);

        //3.判断当前state还剩余名额有几个呀
        //因为我们有一个若抢不到锁则不断循环尝试的过程，有点类似递归的意思，所以必须要有while循环
        //对比SpinLock的while循环：
        //while (!lockOwnerThread.compareAndSet(null, thread)) { }
        while (true) {
            //得看看剩余名额够不够
            int currentSize = state.get();
            if (state.getAndDecrement() > 0) {
                //说明还有剩余名额
                //加入到共享锁集合
                ownerThreads.add(thread);
                //减少一个名额给当前线程
                //state.getAndDecrement();

                break;
            } else {
                //没有名额了，你这个线程下次再来尝试吧(while循环尝试的形式)
                //下次是继续执行上面state.get(),然后判断currentSize>0的逻辑
                //故，在上面加入while 循环
                state.getAndIncrement();
                //使用park需要手动被唤醒，否则while循环不会被继续执行，就中断了
                LockSupport.park();
                //vs使用sleep不需要唤醒，自动被唤醒，然后继续while循环
                //ClockUtil.sleep(0);
            }
        }

        waitQueue.remove(thread);

    }

    @Override
    public void unlock() {
        Thread thread = Thread.currentThread();
        if (!ownerThreads.contains(thread)) {
            throw new IllegalMonitorStateException("线程还没获取锁呢");
        }

        //将state+1并从ownerThreads里退出
        state.getAndIncrement();
        ownerThreads.remove(thread);

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
