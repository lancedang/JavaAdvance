package concurrent.core2.callback;

public interface CallbackTask<T> {
    //执行的数据在哪呢？？
    T execute() throws Exception;

    void onSuccess(T result);

    void onFailure(Throwable throwable);
}
