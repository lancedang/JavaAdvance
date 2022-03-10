package guava.cache;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

@Slf4j
public class CacheDemo {

    public LoadingCache<String, String> riskCacheMapLoaderWithException = CacheBuilder.newBuilder()
            .expireAfterAccess(5, TimeUnit.HOURS)
            .build(new DataLoader2());

    public LoadingCache<String, String> riskCacheMapWithLoader = CacheBuilder.newBuilder()
            .expireAfterAccess(5, TimeUnit.HOURS)
            .build(new DataLoader());

    public Cache<String, String> riskCacheMapNoLoader = CacheBuilder.newBuilder()
            .expireAfterAccess(5, TimeUnit.HOURS)
            .build();

    class DataLoader extends CacheLoader<String, String> {

        @Override
        public String load(String key) throws Exception {
            log.info(Thread.currentThread().getName() + " begin prepare key {} in DataLoader ", key);
            TimeUnit.SECONDS.sleep(5);
            log.info(Thread.currentThread().getName() + " end prepare key {} in DataLoader ", key);
            return key + " 你好";
        }
    }

    class DataLoader2 extends CacheLoader<String, String> {

        @Override
        public String load(String key) throws Exception {
            if (true) {
                throw new RuntimeException("cache demo error");
            }
            return key + " 你好";
        }
    }


    //key不存在cache中时，使用CacheLoader加载，加载抛异常demo
    @Test
    public void test1() {
        try {
            String str = riskCacheMapLoaderWithException.get("qiankai");
            log.info("str=" + str);

        } catch (ExecutionException e) {
            log.error("error", e);
        }
    }

    //单线程依次获取同一个key，后续获取操作很快，前面首次很慢
    @Test
    public void test2() {
        try {
            String threadName = Thread.currentThread().getName();
            String key = "qiankai";

            log.info(threadName + " first begin get key {}", key);
            String str = riskCacheMapWithLoader.get(key);
            log.info(threadName + " first end get key {}, result={}", key, str);

            log.info(threadName + " second begin get key {}", key);
            String str2 = riskCacheMapWithLoader.get(key);
            log.info(threadName + " second end get key {}, result={}", key, str2);

        } catch (ExecutionException e) {
            log.error("error", e);
        }
        //riskCacheMap.getIfPresent()
    }

    //使用cacheLoader验证多线程并行获取相同key demo
    @Test
    public void test3() throws Exception {
        Runnable run1 = () -> {
            for (int i = 0; i < 3; i++) {
                try {
                    log.info(Thread.currentThread().getName() + " begin getting data " + i);
                    String s = riskCacheMapWithLoader.get(i + "");
                    log.info(Thread.currentThread().getName() + " end getting data " + i + " result=" + s);
                } catch (ExecutionException e) {
                    log.error("ExecutionException error, ", e);
                }
            }
        };

        new Thread(run1).start();
        new Thread(run1).start();

        //log.info("status={}", riskCacheMapWithLoader.stats().toString());
        //必须sleep否则test方法直接结束
        TimeUnit.HOURS.sleep(1);
    }

    //错误使用范例，类似HashMap的操作方式,不能保证只有一个线程构造缓存，需要我们人工写synchronize相关保证同步
    @Test
    public void test4() throws Exception {
        Runnable run = () -> {
            String key = "1";

            String threadName = Thread.currentThread().getName();

            log.info(threadName + " begin getting data " + key);

            String s = riskCacheMapNoLoader.getIfPresent(key);
            if (s == null) {
                try {
                    log.info(threadName + " key value not in cache ");
                    TimeUnit.SECONDS.sleep(5);
                    riskCacheMapNoLoader.put(key, threadName);
                    s = riskCacheMapNoLoader.getIfPresent(key);

                    log.info(threadName + " end getting data " + key + " result=" + s);
                } catch (InterruptedException e) {
                    log.error("ExecutionException error, ", e);
                }
            }else {
                log.info(threadName + " getting data in cache " + key + " result=" + s);
            }
        };

        new Thread(run).start();
        new Thread(run).start();

        TimeUnit.HOURS.sleep(1);
    }

    //callable版线程安全test，2个线程有run方法不同value，但实际上相同key,2个最终获取的只有一个相同value
    @Test
    public void test5() throws Exception {

        Runnable run1 = () -> {
            try {
                for (int i = 0; i < 3; i++) {
                    int finalI = i;
                    log.info(Thread.currentThread().getName() + " begin getting data " + i);
                    String s = riskCacheMapNoLoader.get(i + "", () -> finalI + "-caller1");
                    log.info(Thread.currentThread().getName() + " end getting data " + i + " result=" + s);
                }
            } catch (ExecutionException e) {
                log.error("callable1 test error, ", e);
            }
        };

        Runnable run2 = () -> {
            try {
                for (int i = 0; i < 3; i++) {
                    int finalI = i;
                    log.info(Thread.currentThread().getName() + " begin getting data " + i);
                    String s = riskCacheMapNoLoader.get(i + "", () -> finalI + "-caller2");
                    log.info(Thread.currentThread().getName() + " end getting data " + i + " result=" + s);
                }
            } catch (ExecutionException e) {
                log.error("callable1 test error, ", e);
            }
        };

        new Thread(run1).start();
        new Thread(run2).start();

        TimeUnit.HOURS.sleep(1);

    }


}
