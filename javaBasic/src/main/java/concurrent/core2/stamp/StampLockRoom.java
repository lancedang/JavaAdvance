package concurrent.core2.stamp;

import concurrent.core2.ClockUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.collections4.Predicate;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.StampedLock;


@Slf4j
public class StampLockRoom {
    private StampedLock stampedLock = new StampedLock();

    private List<String> workContainer = new ArrayList<>();

    private final int MAX = 1000;
        public void add(String item) {
        Thread thread = Thread.currentThread();
        long stamp = stampedLock.writeLock();
        log.info("{} start add", thread.getName());
        try {
            if (workContainer.size() > MAX) {
                return;
            }
            workContainer.add(item);
            ClockUtil.sleep(10);
        } finally {
            //.writeLock()和.unlockWrite(long)必须成双成对，比翼双飞
            stampedLock.unlockWrite(stamp);
            log.info("{} end add", thread.getName());
        }
    }

    public List<String> search(Predicate predicate) {
        Thread thread = Thread.currentThread();
        long stamp = stampedLock.readLock();
        log.info("{} start read", thread.getName());
        try {
            ClockUtil.sleep(5);
            //这个写法也需要吸收下
            return (List<String>)CollectionUtils.select(workContainer, predicate);
        } finally {
            stampedLock.unlock(stamp);
            log.info("{} end read", thread.getName());
        }
    }



}
