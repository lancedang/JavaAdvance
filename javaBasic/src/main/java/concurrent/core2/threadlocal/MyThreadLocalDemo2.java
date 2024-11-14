package concurrent.core2.threadlocal;

import concurrent.core2.weakreference.upper.A;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Slf4j
public class MyThreadLocalDemo2 {
    public static void main(String[] args) throws NoSuchFieldException, IllegalAccessException, InterruptedException {

        Thread thread1 = new Thread(() -> {
            try {
                test("aaa", false);
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

    public static void test(String s, boolean isGc) throws NoSuchFieldException, IllegalAccessException {

        Thread thread = Thread.currentThread();

        ThreadLocal<Object> local = new ThreadLocal<>();

        Object o = local.get();

        System.out.println(o);

        ThreadLocal<String> threadLocal = new ThreadLocal<>();



        threadLocal.set(s);

        if (isGc) {
            System.gc();
        }


        Class<? extends Thread> aClass = thread.getClass();

        //获取属性
        Field threadLocalsField = aClass.getDeclaredField("threadLocals");

        threadLocalsField.setAccessible(true);

        //通过属性Filed获取这个属性的真实值
        Object threadLocalMapObject = threadLocalsField.get(thread);

        Class<?> threadLocalMapClass = threadLocalMapObject.getClass();
        Field tableField = threadLocalMapClass.getDeclaredField("table");

        tableField.setAccessible(true);
        Object[] tableEntryObjectArray = (Object[]) tableField.get(threadLocalMapObject);

        for (Object entry : tableEntryObjectArray) {
            //entry有一个value，和继承自父类的referent属性
            if (Objects.isNull(entry)) {
                continue;
            }
            Class<?> entryClass = entry.getClass();
            //entry自己定义的可以直接获取
            Field entryValueField = entryClass.getDeclaredField("value");
            entryValueField.setAccessible(true);
            Object entryValue = entryValueField.get(entry);

            //referent是父类的，需要使用父类的class获取,准确的 说是父类的父类
            //这个属性也就是通常说的key
            //这个很重要：referent指向的是被weakReference封装的真实对象
            Field referentFiled = entryClass.getSuperclass().getSuperclass().getDeclaredField("referent");
            referentFiled.setAccessible(true);
            Object entryKey = referentFiled.get(entry);

            //log.info("threadLocalMap key={}, value={}", entryKey, entryValue);
            System.out.println("threadLocalMap map的 key是啥= " + entryKey + ";key类型是="+ entryKey.getClass() + "； value又是啥=" + entryValue);

        }


    }

}
