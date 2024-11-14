package concurrent.core2.aqs.spin;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

@Slf4j
public class SpinLock implements Lock {

    /**
     * 使用拥有者 Thread 作为同步状态，比使用一个简单的整数状态可以携带更多信息
     */
    private AtomicReference<Thread> lockOwnerThread = new AtomicReference<>();

    @Override
    public void lock() {

        Thread thread = Thread.currentThread();
        log.info("{}-请求lock", thread.getName());

        //cas自旋获取锁，未使用CLH单向队列环节cas 用户、内核态切换代价
        //并且可以出是不可重入锁，没有判断当下锁属于的线程是不是自己，只有锁已经被获取，不管获取的线程是谁
        while (!lockOwnerThread.compareAndSet(null, thread)) {
            try {
                //如果lockOwnerThread原来不是null,说明有其他小伙伴获取了锁，我这个线程继续尝试cas
                //这里是通过sleep的方式触发cpu重新调度，其实啥也不写，空着也行
                //让出当前剩余的CPU时间片
                Thread.sleep(0);
            } catch (InterruptedException e) {

            }
        }
        log.info("{}-请求lock成功", thread.getName());
    }

    @Override
    public void unlock() {
        Thread thread = Thread.currentThread();
        log.info("{}-释放lock", thread.getName());
        if (lockOwnerThread.get() != thread) {
            return;
        }
        //只有拥有者才能释放锁
        // 设置拥有者为空，这里不需要 compareAndSet，
        // 因为已经通过owner做过线程检查
        lockOwnerThread.set(null);
        log.info("{}-释放lock成功", thread.getName());
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
