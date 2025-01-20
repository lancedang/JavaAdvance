package concurrent.core2.cdlvscylic;

import concurrent.core2.ClockUtil;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Slf4j
public class CountDownLatchDemo {
    public static void main(String[] args) throws InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(4);

        //模拟：我请我们部门所有人喝奶茶，每个同事都得选好了，我才能下单 这种场景
        //每个同事下单就是一次任务
        //我：就是当前所有任务都执行完，才下单
        CountDownLatch countDownLatch = new CountDownLatch(4);
        for (int i = 0; i < 4; i++) {
            executorService.execute(() -> {
                log.info("其他同事：我是我自个选好了，我也步计较其他人选好没有");
                //ClockUtil.sleep(1);
                countDownLatch.countDown();
            });
        }

        countDownLatch.await();
        log.info("我：好，你们都选好了，我下单");
    }

    @Test
    public void buyCoffeeTogetherWithCyclicBarrier() {
        ExecutorService executorService = Executors.newFixedThreadPool(2);
        CyclicBarrier cyclicBarrier = new CyclicBarrier(2, () -> log.info("我：其他人都买好，我可以下单了"));

        for (int i = 0; i < 2; i++) {
            executorService.submit(() -> {

                try {
                    log.info("其他同事：我自个选好了，现在还不知道 其他人选好没有，得等其他人也选好");
                    ClockUtil.sleep(1);
                    cyclicBarrier.await();
                    //这个地方最关键：当其他所有人都选好后，自己这个线程可以自动向下执行
                    log.info("其他同事：好的，现在我知道其他同事都选好了");
                } catch (Exception e) {
                    log.error("error:", e);
                }
            });

        }

        ClockUtil.sleep(10);
    }
}
