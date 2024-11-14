package concurrent.core2.aqs.mock;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.AbstractQueuedSynchronizer;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

@Slf4j
public class SimpleMockLock implements Lock {

    //使用自定义的AQS子类进行锁的获取、释放动作
    private final static Sync sync = new Sync();

    //开启同步器的抢锁流程，若没有抢到锁将节点添加到等待队列的尾部
    @Override
    public void lock() {
        log.info("lock start lock");
        sync.acquire(1);
        log.info("lock end lock");
    }

    @Override
    public void unlock() {
        log.info("lock start unlock");
        //修改队列中当前线程的节点状态为0，启动后继节点的线程执行
        sync.release(1);
        log.info("lock end unlock");
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

    //自定义的内部类：同步器
    //AbstractQueuedSynchronizer.state 表示锁的状态
    // AbstractQueuedSynchronizer.state=1 表示 锁已经被占用
    // AbstractQueuedSynchronizer.state=0 表示 锁没有被占用
    private final static class Sync extends AbstractQueuedSynchronizer {

        //这个是钩子方法
        //这个方法不会自旋-一直等待或者阻塞这种，cas成功与否都直接返回结果
        //这个钩子方法会被 AbstractQueuedSynchronizer 的模板方法调用
        @Override
        protected boolean tryAcquire(int arg) {
            if (compareAndSetState(0, 1)) {
                setExclusiveOwnerThread(Thread.currentThread());
                return true;
            }
            return false;
        }

        @Override
        protected boolean tryRelease(int arg) {
            //判断1：是不是获取了锁的线程执行释放动作
            if (Thread.currentThread() != getExclusiveOwnerThread()) {
                throw new IllegalStateException("不是持有锁的线程执行释放动作");
            }
            //判断2：锁状态是不是真的已经被占用了
            if (getState() == 0) {
                throw new IllegalStateException("锁状态并未被占用");
            }

            //能走到这一步，标志只能是持有锁的线程来操作下面内容了(无多个线程同时操作情况）
            //故无需再使用cas执行下面操作了
            //锁状态改为释放
            //这里必须先setExclusiveOwnerThread后执行setState,否则报错
            //原因是tryAcquire()方法是先判断cas state, 后set owner thread
/*            if (compareAndSetState(0, 1)) {
                setExclusiveOwnerThread(Thread.currentThread());
                return true;
            }*/
            setExclusiveOwnerThread(null);
            setState(0);

            return true;
        }
    }
}





