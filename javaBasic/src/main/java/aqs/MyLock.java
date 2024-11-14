package aqs;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

@Slf4j
public class MyLock implements Lock {
    
    private AtomicBoolean ready = new AtomicBoolean(false);

    private int state = 0;

    @Override
    public void lock() {
        synchronized (this) {
            while (state == 1) {
                try {
                    log.info(Thread.currentThread().getName() + " waiting");
                    this.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {

                }
            }
            log.info(Thread.currentThread().getName() + " get lock success");
            state = 1;
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
    public void unlock() {
        synchronized (this) {
            log.info(Thread.currentThread().getName() + " notify and release lock");
            state = 0;
            this.notifyAll();
        }
    }

    @Override
    public Condition newCondition() {
        return null;
    }
}
