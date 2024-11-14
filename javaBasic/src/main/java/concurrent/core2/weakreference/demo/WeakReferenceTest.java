package concurrent.core2.weakreference.demo;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class WeakReferenceTest {
    public static void main(String[] args) {

        //new Apple()对象被强引用、弱引用都占用

        //apple变量代表强引用
        Apple apple = new Apple();
        //弱引用
        MyWeakReference reference = new MyWeakReference(apple);

        //括号里的new Apple()对象只被若引用指向
        MyWeakReference reference2 = new MyWeakReference(new Apple("apple2"));

        System.gc();

        while (true) {
            if (reference.get() == null) {
                log.info("apple has 回收");
                break;
            } else {
                log.info("apple not 回收");
            }

            if (reference2.get() == null) {
                log.info("apple2 has 回收");
                break;
            } else {
                log.info("apple2 not 回收");
            }

        }

    }
}
