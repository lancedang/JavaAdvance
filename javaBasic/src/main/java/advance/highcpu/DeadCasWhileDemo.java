package advance.highcpu;

import java.util.concurrent.atomic.AtomicInteger;

public class DeadCasWhileDemo {
    public static void main(String[] args) {
        AtomicInteger i = new AtomicInteger(10);

        while (!i.compareAndSet(1, 20)) {
            System.out.println("hello world");
        }
    }
}
