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
public class ThreadLocalDemo {
    public static void main(String[] args) {
        ExecutorService executorService = Executors.newCachedThreadPool();

        ThreadLocal<UserInfo> threadLocal = new ThreadLocal<>();

        for (int i = 0; i < 10; i++) {
            executorService.submit(new Runnable() {
                @Override
                public void run() {
                    UserInfo userInfo = threadLocal.get();
                    if (Objects.isNull(userInfo)) {
                        threadLocal.set(new UserInfo("lisi", 1));
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
