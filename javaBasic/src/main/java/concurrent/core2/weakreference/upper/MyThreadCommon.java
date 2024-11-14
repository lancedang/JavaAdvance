package concurrent.core2.weakreference.upper;

import concurrent.core2.weakreference.mock.ThreadLocalNoWeakReference;
import lombok.Data;

/**
 * 模拟jdk的Thread类
 */
@Data
public class MyThreadCommon extends Thread {
    public MyThreadCommon(String name) {
        super(name);
    }

    public MyThreadCommon(Runnable target, String name) {
        super(target, name);
    }

    //跟jdk的Thread一样，模拟它，jdk的Thread包含jdk自己的ThreadLocalMap
    //这里，我们用我们摸你的MyThreadLocal.MyThreadLocalMap来操作
    A.B localMap;
}
