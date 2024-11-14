package job;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Data
public abstract class AbstractBaseJob<T> implements Runnable {

    //公共属性：任务名称
    protected String jobName;

    //公共属性：任务元数据
    protected T jobParam;

    //公共属性：任务执行器
    protected AbstractJobManager jobManager;

    //公共方法：模板顶层入口方法
    @Override
    public void run() {
        realDo();
    }

    //子类需继承自定义逻辑：
    protected abstract void realDo();

}
