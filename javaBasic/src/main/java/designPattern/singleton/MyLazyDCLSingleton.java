package designPattern.singleton;

//饥饿模式2-有volatile(DCL模式)
public class MyLazyDCLSingleton {

    //这里没有用volatile修饰，也是ok的，因为下面的synchronized粒度较大,
    private static volatile MyLazyDCLSingleton myLazySingleton = null;

    private MyLazyDCLSingleton() {
        System.out.println("This is My LazySingleton construction method.");
    }

    public static MyLazyDCLSingleton getInstance() {
        //第一次null检查(这行代码其他线程可以随时执行，在锁之外)
        if (myLazySingleton == null) {
            synchronized (MyLazyDCLSingleton.class) {
                //第二次null检查
                if (myLazySingleton == null) {
                    //这里thread1 执行下面代码发生指令重排序，
                    //刚好其他线程执行第一个null检查，如此出现线程安全性
                    myLazySingleton = new MyLazyDCLSingleton();
                }
            }
        }
        return myLazySingleton;
    }

    public static void show() {
        System.out.println("My Lazy Singleton static show method.");
    }

}
