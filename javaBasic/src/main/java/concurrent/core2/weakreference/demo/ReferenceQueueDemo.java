package concurrent.core2.weakreference.demo;

import concurrent.core2.ClockUtil;
import lombok.extern.slf4j.Slf4j;

import java.lang.ref.Reference;
import java.lang.ref.ReferenceQueue;
import java.lang.ref.WeakReference;
import java.util.concurrent.TimeUnit;

@Slf4j
public class ReferenceQueueDemo {
    public static void main(String[] args) {

        //存放回收后的weakReference对象
        ReferenceQueue<Apple> referenceQueue = new ReferenceQueue<>();

        //关联ReferenceQueue 实例/就是一个容器，若出现被回收情况，
        //则将弱引用对象放入queue
        WeakReference<Apple> weakReference1 = new WeakReference<>(new Apple("app1"), referenceQueue);
        WeakReference<Apple> weakReference2 = new WeakReference<>(new Apple("app2"), referenceQueue);

        log.info("gc之前");
        log.info("weakReference1={}", weakReference1);
        log.info("weakReference2={}", weakReference2);
        log.info("weakReference1.get()={}", weakReference1.get());
        log.info("weakReference2.get()={}", weakReference2.get());

        Reference<? extends Apple> item = null;

        int size = 0;

        while ((item = referenceQueue.poll()) != null) {
            log.info("queue item before gc={}", item);
            size++;
        }

        log.info("gc前 reference queue 存放数据个数={}", size);
        log.info("----------------------------");
        log.info("开始 gc");

        //手动触发gc
        System.gc();

        ClockUtil.sleep(4);

        log.info("gc之后");

        //启动2个监控线程，查看各自WeakReference指向的对象是否被回收
        new MonitorThread(weakReference2, "apple2").start();
        new MonitorThread(weakReference1, "apple1").start();

        log.info("----------------------------");
        int size2 = 0;
        while ((item = referenceQueue.poll()) != null) {
            log.info("queue item after gc={}", item);
            size2++;
        }
        log.info("gc后 reference queue 存放数据个数={}", size2);

        log.info("weakReference1={}", weakReference1);
        log.info("weakReference2={}", weakReference2);
        log.info("weakReference1.get()={}", weakReference1.get());
        log.info("weakReference2.get()={}", weakReference2.get());

    }

    static class MonitorThread extends Thread {

        private final WeakReference<Apple> weakReference;
        private final String appleName;

        public MonitorThread(WeakReference<Apple> weakReference, String appName) {
            super();
            this.weakReference = weakReference;
            this.appleName = appName;
        }

        @Override
        public void run() {
            try {
                TimeUnit.SECONDS.sleep(5);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            while (true) {
                if (weakReference.get() == null) {
                    log.info("{} 已经被回收", appleName);
                    break;
                } else {
                    log.info("{} 还未被回收", appleName);
                    try {
                        TimeUnit.SECONDS.sleep(5);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }

            }
        }
    }
}
