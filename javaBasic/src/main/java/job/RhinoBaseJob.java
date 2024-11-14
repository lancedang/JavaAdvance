package job;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class RhinoBaseJob extends AbstractBaseJob<String> {

    //Type1类型的模板执行方法
    public void doWithProvider(Type1Provider provider) {
        doWithProviderWithLock(provider);
    }

    private void doWithProviderWithLock(Type1Provider provider) {
        //step-1）redis 分布式锁-加锁
        addLock();

        //step-2）公共操作-从顶层父类获取job 参数
        RhinoBaseResult rhinoBaseResult = provider.sendRequest(jobParam);
        provider.handleRequestResult(rhinoBaseResult);

        //step3）redis 分布式锁-释放锁
        releaseLock();
    }

    @Override
    protected void realDo() {

    }

    //封装Type1BaseJob子类执行逻辑
    protected interface Type1Provider<T extends RhinoBaseResult> {
        //step-1）先请求获取结果，返回类型是T
        T sendRequest(String companyName);

        //step-2）再处理请求结果
        void handleRequestResult(T result);
    }

    private void addLock() {

    }

    private void releaseLock() {

    }


}
