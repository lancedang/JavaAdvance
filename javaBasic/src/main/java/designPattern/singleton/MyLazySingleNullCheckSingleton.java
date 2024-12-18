package designPattern.singleton;

//饥饿模式1-无volatile(非dcl double check lock;或者说是 single check lock)
public class MyLazySingleNullCheckSingleton {

    //这里没有用volatile修饰，也是ok的，因为下面的synchronized粒度较大,
    //且null判断是在synchronized方法里面，thread1在执行myLazySingleton = new XX()就算出现重排序。
    //其他线程也不会出现使用这个myLazySingleton引用的场景
    private static MyLazySingleNullCheckSingleton myLazySingleton = null;

    private MyLazySingleNullCheckSingleton() {
        System.out.println("This is My LazySingleton construction method.");
    }

    //这里只有1次 myLazySingleton == null 的检查，且在syn里面，故无需使用volatile
    public static synchronized MyLazySingleNullCheckSingleton getInstance() {
        if (myLazySingleton == null) {
            myLazySingleton = new MyLazySingleNullCheckSingleton();
        }
        return myLazySingleton;
    }

    public static void show() {
        System.out.println("My Lazy Singleton static show method.");
    }

}
