package advance.job.processexit;

import java.util.concurrent.TimeUnit;

public class ProcessExitDemo {
    public static void main(String[] args) {

        new Thread(() -> {
            while (true) {
                System.out.println(Thread.currentThread().getName() + ":非main非daemon线程:在 while true循环一直执行");
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();

        mainMethod();

    }

    public static void mainMethod() {
        System.out.println("main线程run");
        throw new RuntimeException("main线程抛异常：test main thread throw exception,but other thread run in while true");
    }
}
