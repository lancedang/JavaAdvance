package concurrent.core2.weakreference.demo;

import concurrent.core2.ClockUtil;
import lombok.extern.slf4j.Slf4j;

import java.lang.ref.Reference;
import java.lang.ref.ReferenceQueue;
import java.lang.ref.WeakReference;

@Slf4j
public class ReferenceQueueDemo {
    public static void main(String[] args) {

        ReferenceQueue<Apple> referenceQueue = new ReferenceQueue<>();

        WeakReference<Apple> weakReference = new WeakReference<>(new Apple("app1"), referenceQueue);
        WeakReference<Apple> weakReference2 = new WeakReference<>(new Apple("app2"), referenceQueue);

        log.info("before gc");
        log.info("weakReference={}", weakReference);
        log.info("weakReference2={}", weakReference2);
        log.info("weakReference.get()={}", weakReference.get());
        log.info("weakReference2.get()={}", weakReference2.get());

        Reference<? extends Apple> item = null;

        while ((item = referenceQueue.poll()) != null) {
            log.info("queue item before gc={}", item);
        }

        log.info("start gc");

        System.gc();

        ClockUtil.sleep(4);

        log.info("after gc");
        while (true) {
            ClockUtil.sleep(1);
            if (weakReference.get() == null) {
                log.info("apple1 has 回收");
                break;
            } else {
                log.info("apple1 没有 回收");
            }

            if (weakReference2.get() == null) {
                log.info("apple2 has 回收");
                break;
            } else {
                log.info("apple2 没有 回收");
            }
        }

        while ((item = referenceQueue.poll()) != null) {
            log.info("queue item after gc={}", item);
        }

        log.info("weakReference={}", weakReference);
        log.info("weakReference2={}", weakReference2);
        log.info("weakReference.get()={}", weakReference.get());
        log.info("weakReference2.get()={}", weakReference2.get());

    }
}
