package concurrent.core2.condition;

import concurrent.core2.ClockUtil;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.Callable;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

//使用ReentrantLock和Condition实现生产者消费者模式，
//生产出苹果就可以被消费掉，如果没有苹果则等待，生产结束得唤醒等待的线程
//可消费/可通知的判断是是否存在苹果，存在性，故只需一个苹果
//只使用一个condition对象实例
@Slf4j
public class ReentrantOneConditionDemo {
    public static void main(String[] args) throws Exception {

        //用于标识当前有多少个苹果
        final int[] size = {0};

        ReentrantLock lock = new ReentrantLock();

        //这个condition对象是上面lock实例绑定/关联的
        Condition condition = lock.newCondition();

        Callable<String> consumeCall = new Callable<String>() {
            @Override
            public String call() throws Exception {

                lock.lock();

                String curName = Thread.currentThread().getName();

                try {
                    log.info("{} consumer start", curName);

                    //如果生产的苹果小于=0，则等待
                    if (size[0] <= 0) {
                        log.info("{} consumer awaiting", curName);
                        //使用Condition对象的await()进行阻塞，类似monitorObject.wait()
                        condition.await();
                    }

                    log.info("{} consumer await end and consume", curName);

                    return "consumer success";
                } finally {
                    //消费完减一
                    size[0]--;
                    lock.unlock();
                }
            }
        };

        Callable<String> produceCall = new Callable<String>() {
            @Override
            public String call() throws Exception {

                String curName = Thread.currentThread().getName();

                lock.lock();
                try {
                    log.info("{} producer start", curName);
                    //使用signal/signalAll()进行唤醒
                    //注意不要用成 notify/notifyAll()了，驴唇不对马嘴

                    //生产+1
                    size[0]++;
                    //如果有苹果，则唤醒等待的线程
                    if (size[0] > 0) {
                        log.info("{} producer signal", curName);
                        condition.signal();
                    }
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


//启动两个消费者
        con.start();
        con2.start();

        ClockUtil.sleep(1);

        pro.start();
        pro2.start();


    }
}
