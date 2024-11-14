package concurrent.core2.forkjoin;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;

@Slf4j
public class ForkJoinDemo {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        int MAX = 1*100000;
        AccumulateTask task = new AccumulateTask(1, MAX);

        ForkJoinPool forkJoinPool = new ForkJoinPool();

        long start = System.currentTimeMillis();

        ForkJoinTask<Long> forkJoinTask = forkJoinPool.submit(task);
        Long sum = forkJoinTask.get();

        log.info("fork join cost={}", System.currentTimeMillis() - start);
        log.info("sum={}", sum);

        start = System.currentTimeMillis();
        Long total = 0L;
        for (long i = 0; i <= MAX; i++) {
            total += i;
        }
        log.info("non fork join cost={}", System.currentTimeMillis() - start);
        log.info("total={}", total);

    }
}
