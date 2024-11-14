package concurrent.core2.aqs;

import java.util.concurrent.locks.Lock;

public class IncrementData {
    public static int sum = 0;
    public static void increment(Lock lock) {
        //使用锁进行互斥的++操作
        lock.lock();
        try {
            sum++;
        } finally {
            lock.unlock();
        }
    }
    public static void decrement(Lock lock) {
    }
}
