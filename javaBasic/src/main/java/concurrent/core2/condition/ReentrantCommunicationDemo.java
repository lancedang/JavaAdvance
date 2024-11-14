package concurrent.core2.condition;

import concurrent.core2.ClockUtil;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.Callable;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

@Slf4j
public class ReentrantCommunicationDemo {
    public static void main(String[] args) throws Exception {

        ReentrantLock lock = new ReentrantLock();
        Condition condition = lock.newCondition();

        Callable<String> consumeCall = new Callable<String>() {
            @Override
            public String call() throws Exception {

                lock.lock();

                try {
                    log.info("consumer start");
                    log.info("consumer awaiting");

                    //使用Condition对象的await()进行阻塞，类似monitorObject.wait()
                    condition.await();
                    log.info("consumer await end");

                    return "consumer sucess";
                } finally {
                    lock.unlock();
                }
            }
        };

        Callable<String> produceCall = new Callable<String>() {
            @Override
            public String call() throws Exception {

                Thread thread = Thread.currentThread();

                lock.lock();
                try {
                    log.info("producer start");
                    log.info("producer signal");
                    //使用signal/signalAll()进行唤醒
                    //注意不要用成 notify/notifyAll()了，驴唇不对马嘴
                    condition.signal();
                    return "produce success";
                } finally {
                    lock.unlock();
                }
            }
        };

        Thread pro = new Thread(() -> {
            try {
                produceCall.call();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }, "pro1");

        Thread pro2 = new Thread(() -> {
            try {
                produceCall.call();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }, "pro2");


        Thread con = new Thread(() -> {
            try {
                consumeCall.call();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }, "con1");


        Thread con2 = new Thread(() -> {
            try {
                consumeCall.call();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }, "con2");

        //con.start();

        con.start();
        con2.start();

        ClockUtil.sleep(1);

        pro.start();
        pro2.start();




    }
}
