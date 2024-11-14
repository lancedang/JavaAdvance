package threadDesignPattern.futureTask;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.util.concurrent.ThreadFactoryBuilder;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.Map;
import java.util.concurrent.*;

@Slf4j
public class FutureTaskDemo {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ExecutorService executorService = new ThreadPoolExecutor(
                10,
                10,
                10000L,
                TimeUnit.MILLISECONDS,
                new LinkedBlockingQueue<>(200),
                new ThreadFactoryBuilder().setNameFormat("mytask-thread-%d").build());

        String name = "qiankai";

        List<FutureTask<Map<String, String>>> futureTaskList = create2Task(name);

        //1）先用线程池提交每个FutureTask
        // FutureTask继承了Runnable接口，故可以提交
        futureTaskList.forEach((item) -> executorService.submit(item));

        //2）再用FutureTask get阻塞等待每个task结束
        //FutureTask实现Future接口
        Map<String, String> resultMap = Maps.newHashMapWithExpectedSize(futureTaskList.size());

        for (FutureTask<Map<String, String>> task : futureTaskList) {
            //future get 阻塞等待每个结果
            Map<String, String> stringStringMap = task.get();
            //统一存放
            resultMap.putAll(stringStringMap);
        }

        log.info(resultMap+"");


    }


    public static List<FutureTask<Map<String, String>>> create2Task(String name) {
        List<FutureTask<Map<String, String>>> taskList = Lists.newArrayList();

        taskList.add(new FutureTask<>(new Callable<Map<String, String>>() {
            @Override
            public Map<String, String> call() throws Exception {
                Map<String, String> map1 = Maps.newHashMap();
                map1.put("age", findAge(name));
                return map1;
            }
        }));


        taskList.add(new FutureTask<>(new Callable<Map<String, String>>() {
            @Override
            public Map<String, String> call() throws Exception {
                Map<String, String> map2 = Maps.newHashMap();
                map2.put("height", findHeight(name));
                return map2;
            }
        }));

        return taskList;
    }

    //任务1
    public static String findAge(String name) throws InterruptedException {
        log.info(Thread.currentThread().getName() + " execute findAge");
        TimeUnit.SECONDS.sleep(2);
        return name + "-age";
    }
    //任务2
    public static String findHeight(String name) throws InterruptedException {
        log.info(Thread.currentThread().getName() + " execute findHeight");
        TimeUnit.SECONDS.sleep(4);
        return name + "-height";
    }
}
