package concurrent.core2.aqs;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ConditionDemo {
    public static void main(String[] args) {
        Lock lock = new ReentrantLock();
        //等待队列1
        Condition condition1 = lock.newCondition();
        //等待队列2
        Condition condition2 = lock.newCondition();
    }
}
