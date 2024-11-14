package concurrent.core2.weakreference.mock;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

@Data
@Slf4j
@AllArgsConstructor
@NoArgsConstructor
public class ThreadLocalWithWeakReference {

    private String name;

    public void set(Object value) {
        Thread thread = Thread.currentThread();
        MyThread2 myThread = (MyThread2) thread;
        MyThreadLocalMap localMap = myThread.getLocalMap();

        if (localMap != null) {
            localMap.set(this, value);
        } else {
            myThread.setLocalMap(new MyThreadLocalMap());
            myThread.getLocalMap().set(this, value);
        }

    }

    public Object get() {
        Thread thread = Thread.currentThread();
        MyThread2 myThread = (MyThread2) thread;
        MyThreadLocalMap localMap = myThread.getLocalMap();

        if (localMap != null) {
            return localMap.get(this);
        }
        return null;
    }

    //MyThreadLocalMap的2种写法-写法1-同jdk
    static class MyThreadLocalMap2 {
        //这个地方是与 MyThreadLocalNoWeakReferenceEntry 有核心区别的地方
        //即MyEntry继承了WeakReference,
        //这地方要注意WeakReference封装的是 ThreadLocalWithWeakReference类型
        //啥意思呢？ThreadLocalWithWeakReference类型的对象若没有强引用来指向的时候
        //不管内存充足与否是可以被gc回收的
        static class MyEntry extends WeakReference<ThreadLocalWithWeakReference> {
            //MyThreadLocal2 key;
            Object value;

            MyEntry(ThreadLocalWithWeakReference threadLocal, Object v) {
                super(threadLocal);
                value = v;
            }
        }


        List<MyEntry> entryList = new ArrayList<>();

        private void set(ThreadLocalWithWeakReference key, Object value) {
            entryList.add(new MyEntry(key, value));
        }

        private Object get(ThreadLocalWithWeakReference key) {
            return entryList.stream().filter(item -> item.get() == key).findFirst().get();
        }

    }


    //MyThreadLocalMap的2种写法-写法2-自定义弱引用
    static class MyReference extends WeakReference<ThreadLocalWithWeakReference> {
        public MyReference(ThreadLocalWithWeakReference referent) {
            super(referent);
        }
    }

    //MyThreadLocalMap的2种写法-写法2-最近发展区写法
    static class MyThreadLocalMap {
        //这个地方是与 MyThreadLocalNoWeakReferenceEntry 有核心区别的地方
        //即MyEntry继承了WeakReference,
        //这地方要注意WeakReference封装的是 ThreadLocalWithWeakReference类型
        //啥意思呢？ThreadLocalWithWeakReference类型的对象若没有强引用来指向的时候
        //不管内存充足与否是可以被gc回收的
        static class MyEntry {

            //这个地方通过属性的方式使用weakReference,而非MyEntry继承WeakReference
            MyReference key;
            Object value;

            MyEntry(ThreadLocalWithWeakReference threadLocal, Object v) {
                //这个地方创造MyReference
                key = new MyReference(threadLocal);
                value = v;
            }
        }

        //一个threadLocalMap有一个list(jdk是有一个Entry[] 数组）
        List<MyEntry> entryList = new ArrayList<>();

        private void set(ThreadLocalWithWeakReference key, Object value) {
            entryList.add(new MyEntry(key, value));
        }

        private Object get(ThreadLocalWithWeakReference key) {
            //获取key的方式稍微调整下
            return entryList.stream().filter(item -> item.key.get() == key).findFirst().get();
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
