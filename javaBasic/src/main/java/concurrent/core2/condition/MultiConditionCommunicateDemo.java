package concurrent.core2.condition;

import concurrent.core2.ClockUtil;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

@Slf4j
public class MultiConditionCommunicateDemo {

    public static final int MAX = 10;

    //描述一个仓储空间，可以存、取东西
    public static class StuffStore<T> {
        //存储空间
        private List<T> storeList = new ArrayList<>();

        private volatile int amount = 0;

        //mutex
        private ReentrantLock lock = new ReentrantLock();

        //标识list不满-可以add数据
        private Condition not_full = lock.newCondition();

        //标志list不空-可以消费数据
        private Condition not_empty = lock.newCondition();

        public void add(T item) {
            lock.lock();
            log.info("start to add lock");
            try {
                //超过最大容量，已满，等待吧
                //guarded suspension模式-必须用while
                while (amount >= MAX) {
                    log.info("start to add await");
                    //等待不满信号
                    not_full.await();
                }
                //否则，可以插入
                log.info("start to add item");

                ClockUtil.sleep(2);
                storeList.add(item);
                amount++;
                //发送非空信号
                not_empty.signal();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                log.info("end add unlock");
                lock.unlock();
            }
        }

        public T get() throws InterruptedException {
            lock.lock();
            log.info("start to get lock");
            try {
                //判断是否为空
                //guarded suspension模式
                while (amount == 0) {
                    log.info("start to get wait");
                    //等待不为空的信号
                    not_empty.await();
                }
                log.info("start to get remove item");
                T remove = storeList.remove(0);

                ClockUtil.sleep(1);

                /*
                方式1：删除第一个元素
                T t = storeList.get(0);
                storeList.removeIf(item -> item == t);
                */
                /*
                方式2：删除第一个元素
                Iterator<T> iterator = storeList.iterator();
                while (iterator.hasNext()) {
                    iterator.next();
                    iterator.remove();
                    //只删除第一个，故break
                    break;
                }*/

                amount --;

                not_full.signal();
                return remove;

            } catch (InterruptedException e) {
                throw e;
            } finally {
                log.info("end get unlock");
                lock.unlock();
            }
        }


    }

    public static void main(String[] args) throws InterruptedException {

        CountDownLatch countDownLatch = new CountDownLatch(2);

        StuffStore<String> store = new StuffStore<>();

        Runnable addRun = new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 20; i++) {
                    Random random = new Random();
                    int j = random.nextInt(10);
                    store.add(i + "");
                    log.info("store amount={}", store.amount);
                }
                countDownLatch.countDown();
            }
        };

        Runnable getRun = new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 20; i++) {
                    try {
                        String s = store.get();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                countDownLatch.countDown();
            }
        };

        Thread thread1 = new Thread(addRun, "add");
        Thread thread2 = new Thread(getRun, "get");

        thread1.start();
        thread2.start();

        countDownLatch.await();
        log.info("size={}", store.storeList.size());

    }
}
