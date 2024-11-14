package concurrent.core2.forkjoin;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;

@Data
@AllArgsConstructor
@Slf4j
public class AccumulateTask extends RecursiveTask<Long> {

    public static final int MAX = 1000;

    private int start;
    private int end;


    @Override
    protected Long compute() {

        Long sum = 0L;

        if (end - start > 10) {

            //log.info("split-{}, {}", start, end);

            //分而治之
            int middle = (end - start) / 2;
            AccumulateTask leftTask = new AccumulateTask(start, start + middle);
            AccumulateTask rightTask = new AccumulateTask(start + middle + 1, end);

            //把左任务分配下去
            ForkJoinTask<Long> left = leftTask.fork();
            //把右任务分配下去
            ForkJoinTask<Long> right = rightTask.fork();

            //等待左右任务都完成
            Long joinLeft = leftTask.join();
            Long joinRight = rightTask.join();

            //左右任务求和
            sum = joinLeft + joinRight;

        } else {
            //数量较小直接累加
            //这要把end纳入范围
            for (int i = start; i <= end; i++) {
                sum += i;
            }
        }

        return sum;
    }
}
