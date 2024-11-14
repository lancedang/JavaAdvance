package guava.retry;

import com.github.rholder.retry.*;
import com.google.common.base.Predicates;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

@Slf4j
public class RetryDemo {

    public static Retryer<Boolean> retryer = RetryerBuilder.<Boolean>newBuilder()
            //抛出runtime异常、checked异常时都会重试，但是抛出error不会重试。
            .retryIfException()
            //返回false也需要重试
            .retryIfResult(Predicates.equalTo(false))
            //重调策略
            .withWaitStrategy(WaitStrategies.fixedWait(2, TimeUnit.SECONDS))
            //尝试次数
            .withStopStrategy(StopStrategies.stopAfterAttempt(4))
            .build();

    public static void main(String[] args) {

        Callable<Boolean> callable = () -> {
            log.info(Thread.currentThread().getName() + " begin getting data ");
            return false;
        };

        try {
            retryer.call(callable);
        } catch (ExecutionException e) {
            log.error("ExecutionException error, ", e);
        } catch (RetryException e) {
            log.error("RetryException error, ", e);
        }

    }
}
