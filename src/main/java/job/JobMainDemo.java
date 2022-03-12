package job;

import com.google.common.collect.Lists;

import java.util.List;

public class JobMainDemo {
    public static void main(String[] args) {

        List<AbstractBaseJob> jobs = Lists.newArrayListWithCapacity(2);

        String jobParam = "小米科技";

        //step-01）定义子job集合
        RhinoGongShangJob gongShangJob = new RhinoGongShangJob();
        gongShangJob.setJobParam(jobParam);

        RhinoLawSuitJob lawSuitJob = new RhinoLawSuitJob();
        lawSuitJob.setJobParam(jobParam);

        jobs.add(gongShangJob);
        jobs.add(lawSuitJob);

        //step-02）准备任务执行器
        RhinoJobManager rhinoJobManager = new RhinoJobManager();
        //交代执行器需要执行的job
        rhinoJobManager.setJobs(jobs);
        rhinoJobManager.init();

        //step-03）运行所有任务
        rhinoJobManager.start();



    }
}
