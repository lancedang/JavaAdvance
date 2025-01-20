package concurrent.core2.stamp;

import concurrent.core2.ClockUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.collections4.Predicate;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.ReentrantReadWriteLock;


@Slf4j
public class ReadWriteLockRoom {

    //模拟：互斥资源，该互斥资源允许并发读，但不允许并发写
    private List<String> workContainer = new ArrayList<>();

    private ReentrantReadWriteLock lock = new ReentrantReadWriteLock();

    private final int MAX = 10000;


    //模拟：并发写操作共享资源（使用互斥/写锁），外界提供item/比如一个个苹果，add操作互斥性的把item添加到容器里
    public void add(String item) {
        Thread thread = Thread.currentThread();
        lock.writeLock().lock();
        try {
            log.info("{} 添加--开始。。。", thread.getName());
            if (workContainer.size() > MAX) {
                return;
            }
            workContainer.add(item);

            ClockUtil.sleep(10);

        } finally {
            lock.writeLock().unlock();
            log.info("{} 添加--结束", thread.getName());
        }
    }

    //模拟：并发读操作共享资源（使用读锁）
    public List<String> search(Predicate predicate) {
        Thread thread = Thread.currentThread();
        lock.readLock().lock();
        log.info("{} 读取--开始", thread.getName());
        try {
            ClockUtil.sleep(5);
            return (List<String>) CollectionUtils.select(workContainer, predicate);
        } finally {
            lock.readLock().unlock();
            log.info("{} 读取--结束", thread.getName());
        }
    }
}
