package concurrent.core2.threadlocal;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.Objects;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Data
@Slf4j
//基础demo,线程池每个线程创建一份数据 存放到 ThreadLocal 中
public class ThreadLocalDemo {
    //整个main的退出要等到所有线程执行完的1分钟之后
    //等到cachedThreadPool的所有线程全部退出(cachedThreadPool默认是线程缓存1分钟/生命周期1分钟)
    public static void main(String[] args) {
        ExecutorService executorService = Executors.newCachedThreadPool();

        ThreadLocal<UserInfo> threadLocal = new ThreadLocal<>();

        for (int i = 0; i < 10; i++) {
            executorService.submit(new Runnable() {
                @Override
                public void run() {

                    Thread thread = Thread.currentThread();

                    UserInfo userInfo = threadLocal.get();
                    if (Objects.isNull(userInfo)) {
                        threadLocal.set(new UserInfo(thread.getName(), 1));
                    }

                    log.info("user={}", threadLocal.get());
                }
            });
        }
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class UserInfo{
        private String name;
        private int age;
    }
}
