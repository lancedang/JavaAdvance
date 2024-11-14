package concurrent.core2.weakreference.mock;

import lombok.Data;

/**
 * 模拟jdk的Thread类
 */
@Data
public class MyThread2 extends Thread {
    public MyThread2(String name) {
        super(name);
    }

    public MyThread2(Runnable target, String name) {
        super(target, name);
    }

    //跟jdk的Thread一样，模拟它，jdk的Thread包含jdk自己的ThreadLocalMap
    //这里，我们用我们摸你的MyThreadLocal.MyThreadLocalMap来操作
    //与MyThread区别是,我们这里用了MyThreadLocal2
    ThreadLocalWithWeakReference.MyThreadLocalMap localMap;
}
