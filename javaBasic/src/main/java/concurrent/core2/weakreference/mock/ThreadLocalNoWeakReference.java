package concurrent.core2.weakreference.mock;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

/**
 * 模拟jdk的ThreadLocal类,
 * 类层次：MyThreadLocal -> MyThreadLocalMap -> MyEntry
 */
@Data
@Slf4j
@AllArgsConstructor
@NoArgsConstructor
public class ThreadLocalNoWeakReference {

    private String name;

    //模拟jdk的ThreadLocal的set操作
    //这里的set对应MyThreadLocalMap的set()
    public void set(Object value) {
        Thread thread = Thread.currentThread();

        //这里转成MyThread
        MyThread myThread = (MyThread) thread;
        MyThreadLocalMap localMap = myThread.getLocalMap();

        if (localMap != null) {
            localMap.set(this, value);
        } else {
            myThread.setLocalMap(new MyThreadLocalMap());
            myThread.getLocalMap().set(this, value);
        }

    }

    //这里的get对应MyThreadLocalMap的get()
    public Object get() {
        Thread thread = Thread.currentThread();
        MyThread myThread = (MyThread) thread;
        MyThreadLocalMap localMap = myThread.getLocalMap();

        if (localMap != null) {
            return localMap.get(this);
        }
        return null;
    }

    //模拟jdk 的ThreadLocalMap类
    @Data
    static class MyThreadLocalMap {

        //模拟jdk的Entry类
        static class MyEntry {

            //这里vs jdk,直接用 ThreadLocalNoWeakReference 类型作为key-value对的key类型
            //jdk是啥?jdk的Entry extend WeakReference
            //我们目的是测试没有使用WeakReference时 对象不会被gc回收
            //啥对象？WeakReference封装的类型对象
            ThreadLocalNoWeakReference key;
            Object value;

            MyEntry(ThreadLocalNoWeakReference threadLocal, Object v) {
                key = threadLocal;
                value = v;
            }
        }


        //我们不搞jdk那么复杂的的map类型了，就用一个list存储key-value对
        List<MyEntry> entryList = new ArrayList<>();

        //ThreadLocalMap提供get/set方法，本质是对list的操作
        //模拟添加操作
        private void set(ThreadLocalNoWeakReference key, Object value) {
            entryList.add(new MyEntry(key, value));
        }

        //模拟查询操作
        private Object get(ThreadLocalNoWeakReference key) {
            return entryList.stream().filter(item -> item.key == key).findFirst().get();
        }

    }

    //这里非常重要，若对象被回收的话，会调用其finalize()方法
    //下面内容打印与否，表明该类的对象是否被回收
    //即ThreadLocal对象
    //明确的大前提：是哪种类型的对象造成了内存泄漏？哪类对象没有被回收？
    @Override
    protected void finalize() throws Throwable {
        super.finalize();
        log.info("finalize {}", name);
    }
}
