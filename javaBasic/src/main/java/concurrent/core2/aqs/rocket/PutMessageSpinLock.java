package concurrent.core2.aqs.rocket;

import java.util.concurrent.atomic.AtomicBoolean;

public class PutMessageSpinLock  {
    //true: Can lock, false : in lock.
    private AtomicBoolean putMessageSpinLock = new AtomicBoolean(true);


    public void lock() {
        boolean flag;
        do {
            flag = this.putMessageSpinLock.compareAndSet(true, false);
        }
        while (!flag);
    }


    public void unlock() {
        this.putMessageSpinLock.compareAndSet(false, true);
    }
}