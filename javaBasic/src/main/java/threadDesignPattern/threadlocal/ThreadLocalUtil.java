package threadDesignPattern.threadlocal;

import java.util.Objects;

public class ThreadLocalUtil {

    private static ThreadLocal<MyLog> threadLocal = new ThreadLocal<>();

    public static void print(String string) {
        getMyLog().print(string);
    }

    public static void close() {
        getMyLog().close();
    }

    public static MyLog getMyLog() {
        MyLog myLog = threadLocal.get();
        if (Objects.isNull(myLog)) {
            threadLocal.set(new MyLog(Thread.currentThread().getName()));
        }
        return threadLocal.get();
    }

}
