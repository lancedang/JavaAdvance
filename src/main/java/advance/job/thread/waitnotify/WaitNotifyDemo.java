package advance.job.thread.waitnotify;

/**
 * 考虑waitting状态线程n个，notify()方法执行m次，n>m的情况-（n-m）个线程永久处于waitting状态
 */
public class WaitNotifyDemo {

    private final static Object lock = new Object();

    public void myWait() {
        synchronized (lock) {
            try {
                System.out.println(Thread.currentThread() + "wait begin");

                //注意，synchronized是哪个锁，则调用那个锁的wait或notify
                //下面的代码意味着调用this.wait(),是this对象锁，而非上面定义的lock对象
                //会报IllegalMonitorStateException异常
                //wait();
                lock.wait();
                System.out.println(Thread.currentThread() + "wait end");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void myNotify() {
        synchronized (lock) {
            System.out.println("notify begin");
            for (int i = 0; i < 3; i++) {
                //执行n次，唤醒n个waitting状态的线程，若n小于 处于waitting的线程总数设为m，则有m-n个线程
                //永久处于waitting状态
                lock.notify();
            }
            System.out.println("notify end");
        }
    }

    public void myNotifyAll() {
        synchronized (lock) {
            System.out.println("notify begin");
            lock.notifyAll();
            System.out.println("notify end");
        }
    }

    public static void main(String[] args) {

        WaitNotifyDemo waitNotifyDemo = new WaitNotifyDemo();

        Thread waitThread1 = new Thread(() -> waitNotifyDemo.myWait(), "thread1");
        Thread waitThread2 = new Thread(() -> waitNotifyDemo.myWait(), "thread2");
        Thread waitThread3 = new Thread(() -> waitNotifyDemo.myWait(), "thread3");

        Thread notifyThread1 = new Thread(() -> waitNotifyDemo.myNotify());
        Thread notifyThread2 = new Thread(() -> waitNotifyDemo.myNotifyAll());

        waitThread2.start();
        waitThread3.start();
        waitThread1.start();


        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //notify()
        //notifyThread1.start();

        //notifyAll
        notifyThread2.start();
    }

}
