package concurrent.core2.atom;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;

@Data
@Slf4j
public class AtomicFieldUpdateDemo {
    public static void main(String[] args) throws InterruptedException {

        ExecutorService safeExecutorService = Executors.newCachedThreadPool();
        ExecutorService unsafeExecutorService = Executors.newCachedThreadPool();
        ExecutorService innerExecutorService = Executors.newCachedThreadPool();

        CountDownLatch countDownLatch = new CountDownLatch(10);
        CountDownLatch countDownLatch2 = new CountDownLatch(20);

        Person unsafePerson = new Person("lisi", 0);
        Person safePerson = new Person("lisi", 0);

        InnerPerson innerPerson = new InnerPerson("lisi", new AtomicInteger(0));

        AtomicIntegerFieldUpdater<Person> updater =
                AtomicIntegerFieldUpdater.newUpdater(Person.class, "total");

        Runnable unsafeRun = () -> {
            for (int i = 0; i < 1000; i++) {
                //对比，参照
                unsafePerson.setTotal(unsafePerson.total++);
            }
            countDownLatch2.countDown();
        };

        Runnable safeRun = () -> {
            for (int i = 0; i < 1000; i++) {
                //其实就是外部版的AtomicInteger使用方式
                //啥是内部版呢，就是Person  AtomicInteger total
                updater.getAndIncrement(safePerson);
            }
            countDownLatch.countDown();
        };

        Runnable innerRun = () -> {
            for (int i = 0; i < 1000; i++) {
                //
                //内部版呢，就是Person  AtomicInteger total
                innerPerson.add();
            }
            countDownLatch2.countDown();
        };


        for (int i = 0; i < 10; i++) {
            unsafeExecutorService.submit(unsafeRun);
        }


        for (int i = 0; i < 10; i++) {
            innerExecutorService.submit(innerRun);
        }


        countDownLatch2.await();
        log.info("unsafe total={}", unsafePerson.total);
        log.info("inner total={}", innerPerson.total.get());


        for (int i = 0; i < 10; i++) {
            safeExecutorService.submit(safeRun);
        }

        countDownLatch.await();

        log.info("safe total={}", safePerson.total);

    }


    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Person {
        private String name;
        //使用AtomicFieldUpdate的变量必须是public volatile
        public volatile int total;
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class InnerPerson {
        private String name;
        //内部原子自增类
        private AtomicInteger total;

        public void add() {
            //内部版：使用atomic自增
            //但是
            total.getAndIncrement();
        }

    }
}
