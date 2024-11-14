package concurrent.core2.workermaster;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.atomic.LongAdder;
import java.util.function.Consumer;

/**
 * 定义一个任务的抽象层
 * R表示返回结果类型
 *
 * @param <R>
 */
@Data
@Slf4j
public class Task<R> {
    //自增器，因为是所有task的id递增，故是static修饰
    //尝试下LongAdder吧，刻意练习下新东西，亦可用AtomicInteger等(后者其实更常用)
    private static LongAdder taskIdAdder = new LongAdder();

    //封装task的执行结果
    private R result;

    //封装task后置处理器
    private Consumer<Task<R>> taskConsumer;

    private long taskId;

    public Task() {
        taskIdAdder.increment();
        this.taskId = taskIdAdder.longValue();
    }

    //模板方法而已
    //step1:执行
    //step2:后置处理
    public R execute() {
        result = doExecute();
        taskConsumer.accept(this);
        return result;
    }

    //封装task的真实处理逻辑
    protected R doExecute() {
        return null;
    }

}
