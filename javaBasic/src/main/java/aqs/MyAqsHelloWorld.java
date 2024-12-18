package aqs;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.AbstractQueuedSynchronizer;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

@Slf4j
//层1：lock实现类
public class MyAqsHelloWorld implements Lock {

    //
    private HelloWorldAQS sync;

    public MyAqsHelloWorld() {
        this.sync = new HelloWorldAQS();
    }

    @Override
    public void lock() {
        sync.acquire(1);
    }

    @Override
    public void lockInterruptibly() throws InterruptedException {

    }

    @Override
    public boolean tryLock() {
        return sync.tryAcquire(1);
    }

    @Override
    public boolean tryLock(long time, TimeUnit unit) throws InterruptedException {
        return false;
    }

    @Override
    public void unlock() {
        sync.release(1);
    }

    @Override
    public Condition newCondition() {
        return null;
    }

    //层2：AQSImpl层
    class HelloWorldAQS extends AbstractQueuedSynchronizer {

        //1.使用aqs唯一途径就是这几个方法，
        //其余方法都是final private不可继承
        //2.这些方法原则上是**非阻塞的**
        @Override
        protected boolean tryAcquire(int arg) {
            Thread thread = Thread.currentThread();

            log.info("{} 尝试-获取锁", thread.getName());

            //调用父类的cas 模板方法来操作aqs自身定义的state
            if (super.compareAndSetState(0, arg)) {
                //设置当前锁所属线程
                super.setExclusiveOwnerThread(thread);
                log.info("{} 获取锁---成功", thread.getName());
                return true;
            }

            return false;
        }

        @Override
        protected boolean tryRelease(int arg) {

            Thread thread = Thread.currentThread();
            log.info("{} 尝试-释放锁", thread.getName());

            if (super.getExclusiveOwnerThread() != thread) {
                throw new RuntimeException("not hold lock");
            }

            if (super.compareAndSetState(arg, 0)) {
                log.info("{} 释放锁---成功", thread.getName());
                return true;
            }

            return false;
        }

        @Override
        //涉及ConditionObject的地方才用到这个方法
        //如果没用到condition queue的地方可以不实现该方法
        protected boolean isHeldExclusively() {
            return super.getState() == 1 && super.getExclusiveOwnerThread() == Thread.currentThread();
        }
    }

    public static void main(String[] args) {

        MyAqsHelloWorld helloWorldLock = new MyAqsHelloWorld();

        Runnable task = new Runnable() {
            @Override
            public void run() {
                try {
                    helloWorldLock.lock();

                    log.info(Thread.currentThread().getName() + " running task");
                    TimeUnit.SECONDS.sleep(1000);

                } catch (Exception e) {
                    log.error("error: ", e);
                } finally {
                    helloWorldLock.unlock();
                }

            }
        };

        new Thread(task, "t-A").start();
        new Thread(task, "t-B").start();
        new Thread(task, "t-C").start();

    }
}
