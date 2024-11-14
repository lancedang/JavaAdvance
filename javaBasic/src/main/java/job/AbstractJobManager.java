package job;

import com.google.common.util.concurrent.ThreadFactoryBuilder;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.concurrent.*;

@Slf4j
public abstract class AbstractJobManager {

    //公共属性：盛放所有任务
    protected List<AbstractBaseJob> jobs;

    //公共属性：线程池-任务执行器
    protected ExecutorService executorService;

    protected void init() {
        ThreadFactory threadFactory = new ThreadFactoryBuilder().setNameFormat("jobThread-%d").build();
        executorService = new ThreadPoolExecutor(10, 10, 1L, TimeUnit.SECONDS, new LinkedBlockingQueue<>(100), threadFactory);
    }

    public void setJobs(List<AbstractBaseJob> jobs) {
        this.jobs = jobs;
    }

    public void start() {
        jobs.forEach(executorService::submit);
    }


    public void stop() {
        executorService.shutdown();
    }
}
