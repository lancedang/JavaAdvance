package concurrent.core2.threadlocal;

import concurrent.core2.ClockUtil;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.lang.reflect.Field;
import java.util.Objects;

@Slf4j
public class MyThreadLocalDemo2 {
    public static void main2(String[] args) throws NoSuchFieldException, IllegalAccessException, InterruptedException {

        Thread thread1 = new Thread(() -> {
            try {
                checkKeyIsNull("aaa", false);
            } catch (NoSuchFieldException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        });

        thread1.start();

        thread1.join();
        //log.info("main");
        System.out.println("main");
    }

    public static void checkKeyIsNull(String yourData, boolean isGc) throws NoSuchFieldException, IllegalAccessException {

        Thread thread = Thread.currentThread();

//        ThreadLocal<Object> local = new ThreadLocal<>();
//
//        Object o = local.get();
//
//        System.out.println(o);

        ThreadLocal<String> threadLocal = new ThreadLocal<>();

        threadLocal.set(yourData);

        if (isGc) {
            System.gc();
        }

        showEntryInThread(thread);


    }

    //目的：gc时，判断某个线程的threadLocal实例(也就是key) 是否为null
    //背景：Thread内部的threadLocals 成员变量，没有通过api暴露出来，
    //也就无法直接查看其内部的entry数组的key
    //(key即threadLocal实例，也是Entry父类WeakReference的referent属性）
    //故，下面通过，对Thread进行反射，一步步获取其entry数组，进而找到数组每个元素的key

    //Thread->ThreadLocal.ThreadLocalMap->Entry[]->(value比较直接)
    //Thread->ThreadLocal.ThreadLocalMap->Entry[]->父WeakReference->referent(threadLocal实例)
    private static void showEntryInThread(Thread thread) throws NoSuchFieldException, IllegalAccessException {
        Class<? extends Thread> aClass = thread.getClass();

        //step1: 先获取Thread类的成员变量-threadLocals（这个是ThreadLocalMap类型的）
        //Thread 源代码：ThreadLocal.ThreadLocalMap threadLocals = null;
        Field threadLocalsField = aClass.getDeclaredField("threadLocals");

        threadLocalsField.setAccessible(true);

        //通过属性Filed获取这个属性的真实值
        Object threadLocalMapObject = threadLocalsField.get(thread);

        Class<?> threadLocalMapClass = threadLocalMapObject.getClass();
        //step2： 再获取ThreadLocalMap的成员变量-table
        Field tableField = threadLocalMapClass.getDeclaredField("table");

        tableField.setAccessible(true);
        Object[] tableEntryObjectArray = (Object[]) tableField.get(threadLocalMapObject);

        //step3: 遍历这个table数组
        for (Object entry : tableEntryObjectArray) {
            //entry有一个value，和继承自父类的referent属性
            if (Objects.isNull(entry)) {
                continue;
            }

            //step4： entry是继承自WeakReference的子类
            Class<?> entryClass = entry.getClass();

            //step4-1: entry自己定义的属性可以直接获取
            Field entryValueField = entryClass.getDeclaredField("value");
            entryValueField.setAccessible(true);
            Object entryValue = entryValueField.get(entry);

            //step4-2: entry 父类WeakReference的属性referent 获取之
            //最终的目的：是判断这个referent是否为null
            //referent是父类的，需要使用父类的class获取,准确的 说是父类的父类
            //这个属性也就是通常说的key
            //这个很重要：referent指向的是被weakReference封装的真实对象
            Field referentFiled = entryClass.getSuperclass().getSuperclass().getDeclaredField("referent");
            referentFiled.setAccessible(true);
            Object entryKey = referentFiled.get(entry);

            if (entryKey != null) {
                if (entryKey.getClass() != ThreadLocal.class) {
                    continue;
                }

                //log.info("threadLo   calMap key={}, value={}", entryKey, entryValue);
                log.info("threadLocalMap map的 key={},key类型是={}, value又是啥={}", entryKey, entryKey.getClass(), entryValue);
            } else {
                log.info("threadLocalMap entry 的entryKey是null");
            }

        }
    }

    @Test
    //场景1：无强引用指向 ThreadLocal()实例，查看gc前后 该对象 是否被回收
    public void testNoStrongYinyong() {
        Runnable noStrongYinYong = new Runnable() {
            @Override
            public void run() {
                Thread curThread = Thread.currentThread();

                //1.先给当前线程设置一个threadLocal, 形式：直接使用new ThreadLocal()这玩意
                //以验证 new ThreadLocal()这个对象 无强引用情况下，gc 前后，
                // ThreadLocalMap 的Entry[]数组里，以这个对象(new ThreadLocal())为key的key会不会被回收
                new ThreadLocal<String>().set("业务数据");

                try {
                    log.info("gc前，查看 new ThreadLocal()形式的key在Entry数组中是否为null");
                    showEntryInThread(curThread);

                    log.info("--------------------------");

                    log.info("gc后，查看 new ThreadLocal()形式的key在Entry数组中是否为null");
                    System.gc();
                    showEntryInThread(curThread);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        };

        new Thread(noStrongYinYong).start();

        ClockUtil.sleep(1000);
    }

    @Test
    public void testHasStrongYinyong() {
        Runnable hasStrongYinYong = new Runnable() {
            @Override
            public void run() {
                Thread curThread = Thread.currentThread();

                //1.先给当前线程设置一个threadLocal,
                // 形式：使用变量接收 new ThreadLocal()，类似 XX x = new XX() 使用x变量保证存在强引用
                //以验证 存在强引用情况下，gc 前后，
                // ThreadLocalMap 的Entry[]数组里，以这个对象(new ThreadLocal())为key的key会不会被回收
                ThreadLocal<String> threadLocal = new ThreadLocal<>();
                threadLocal.set("业务数据");

                try {
                    log.info("gc前，查看 new ThreadLocal()形式的key在Entry数组中是否为null");
                    showEntryInThread(curThread);

                    log.info("--------------------------");

                    log.info("gc后，查看 new ThreadLocal()形式的key在Entry数组中是否为null");
                    System.gc();
                    showEntryInThread(curThread);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        };

        new Thread(hasStrongYinYong).start();

        ClockUtil.sleep(1000);
    }
}
