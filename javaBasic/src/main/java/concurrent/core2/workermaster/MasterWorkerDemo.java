package concurrent.core2.workermaster;

import concurrent.core2.ClockUtil;
import lombok.extern.slf4j.Slf4j;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Slf4j
public class MasterWorkerDemo {

    //存放执行结果
    //通过task 后置回调收集结果
    //可以把结果处理放到masterworker类中
    static Map<Long, String> resultMap = new ConcurrentHashMap<>();

    public static void main(String[] args) {

        MasterWorker<LittleTask> masterWorker = new MasterWorker<>(5);

        //起一个线程，定时打印统计的结果
        new Thread(() -> {
            while (true) {
                log.info("" + resultMap);
                ClockUtil.sleep(2);
            }
        }
        ).start();

        //while true模拟任务制造器（producer这玩意）
        while (true) {
            LittleTask littleTask = new LittleTask();
            littleTask.setTaskConsumer(MasterWorkerDemo::taskEndDeal);

            //将任务提交到master自己的队列
            masterWorker.submit(littleTask);
            ClockUtil.sleep(1);
        }


    }

    //task的后置处理
    public static void taskEndDeal(Task<String> task) {
        log.info("end task deal {}", task.getTaskId());
        //统一后置处理，汇总每个task结果
        String result = task.getResult();
        resultMap.put(task.getTaskId(), result);
    }

    //自定义Task类，返回值类型是String
    public static class LittleTask extends Task<String> {

        @Override
        protected String doExecute() {

            log.info("this is {}", getTaskId());
            //模拟每个task返回的记过
            return "hi:" + getTaskId();
        }
    }
}
