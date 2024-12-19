package concurrent.core2.aqs.locksupport;


import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.LockSupport;
import java.util.concurrent.locks.ReentrantLock;

//LockSupport提供更底层/更基础/任意位置/不限于锁的唤醒通知机制
//但，LockSupport需开发者自行控制线程安全性
//此处使用LockSupport实现一个涉及线程安全的简单的生产者-消费者模式()
@Slf4j
public class LockSupportWithLockDemo {
    private static int size = 0;

    private static final ReentrantLock reentrantLock = new ReentrantLock();

    //这个是LockSupport结合ReentrantLock的demo
    //这个是错误/不完整代码，因为一般阻塞线程/调用LockSupport.park()时会使当前线程释放锁
    //这个释放所得动作必须开发者本人来实现/而且比较复杂，故这个demo没有实现
    public static void main(String[] args) throws InterruptedException {

        Thread consumerThread = new Thread(new Runnable() {
            @Override
            public void run() {
                reentrantLock.lock();
                try {
                    if (size == 0) {
                        //如果无数据，则阻塞当前消费者
                        log.info("消费者：无数据，我要阻塞了");
                        //这个地方，要手动释放锁，否则，其他线程/生产者线程无法继续执行
                        //仔细思考，condition.await()/synchronize为啥不需要下面额外/手动释放锁的代码
                        //reentrantLock.unlock();
                        LockSupport.park();

                    }
                    log.info("消费者：消费1个");
                } finally {
                    reentrantLock.unlock();
                }
            }
        }, "消费者");

        Thread produceThread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    reentrantLock.lock();
                    log.info("producer: 我生产了一个");
                    size ++;
                    LockSupport.unpark(consumerThread);
                } finally {
                    reentrantLock.unlock();
                }
            }
        }, "生产者");

        consumerThread.start();

        TimeUnit.SECONDS.sleep(1);

        produceThread.start();

    }

    //这个是传统的/正确结果的/ ReentrantLock结合condition的生产者-消费者模式
    //本例知识点：await()方法会自动释放锁（但是这个释放锁不是调用reentrantLock.unlock()方法）
    public static void main2(String[] args) throws InterruptedException {

        Condition condition = reentrantLock.newCondition();

        Thread consumerThread = new Thread(new Runnable() {
            @Override
            public void run() {
                reentrantLock.lock();
                try {
                    if (size == 0) {
                        //如果无数据，则阻塞当前消费者
                        log.info("消费者：无数据，我要阻塞了");
                        //1.这个地方，await()之后，会自动释放锁
                        //其实，正确的逻辑就是必须释放当前的ReentrantLock锁，
                        //否则 producer线程无法获取锁/也就无法执行生产动作了
                        //2.我们已知，unlock()方法其实就是释放锁的逻辑，
                        //那其实意味着，await()这个方法的内部 有何 unlock()方法内部相同的逻辑
                        //即，这两者都会执行底层释放锁的逻辑
                        //debug进入到await()方法内部，可以发现，await()方法内部，和unlock()方法内部
                        //使用的是公共的AQS的release()方法
                        condition.await();
                    }
                    log.info("消费者：消费1个");
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                } finally {
                    reentrantLock.unlock();
                }
            }
        }, "消费者");

        Thread produceThread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    reentrantLock.lock();
                    log.info("producer: 我生产了一个");
                    size ++;
                    //LockSupport.unpark(consumerThread);
                    condition.signal();
                } finally {
                    reentrantLock.unlock();
                }
            }
        }, "生产者");

        consumerThread.start();

        TimeUnit.SECONDS.sleep(1);

        produceThread.start();

    }
}
