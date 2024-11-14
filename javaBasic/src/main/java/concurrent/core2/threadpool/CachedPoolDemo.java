package concurrent.core2.threadpool;

import concurrent.core2.ClockUtil;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CachedPoolDemo {
    public static void main(String[] args) {
        ExecutorService executorService = Executors.newCachedThreadPool();

        executorService.execute(() -> System.out.println("xx"));

        ClockUtil.sleep(5);

        executorService.execute(() -> System.out.println("yy"));

        System.out.println("main end");

    }
}
