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

    private List<String> workContainer = new ArrayList<>();

    private ReentrantReadWriteLock lock = new ReentrantReadWriteLock();

    private final int MAX = 10000;


    public void add(String item) {
        Thread thread = Thread.currentThread();
        lock.writeLock().lock();
        try {
            log.info("{} start add", thread.getName());
            if (workContainer.size() > MAX) {
                return;
            }
            workContainer.add(item);

            ClockUtil.sleep(10);

        } finally {
            lock.writeLock().unlock();
            log.info("{} end add", thread.getName());
        }
    }

    public List<String> search(Predicate predicate) {
        Thread thread = Thread.currentThread();
        lock.readLock().lock();
        log.info("{} start read", thread.getName());
        try {
            ClockUtil.sleep(5);
            return (List<String>) CollectionUtils.select(workContainer, predicate);
        } finally {
            lock.readLock().unlock();
            log.info("{} end read", thread.getName());
        }
    }
}
