package advance.job;

/**
 * 死锁案例，资源循环等待
 *
 */
public class DeadLockDemo {

    private static Object lock1 = new Object();
    private static Object lock2 = new Object();

    public static void main(String[] args) {

        new Thread(() -> {
            synchronized (lock1) {
                try {
                    System.out.println("get lock1");
                    Thread.sleep(20000);

                    synchronized (lock2) {
                        System.out.println("requesting lock2");
                    }

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();

        new Thread(() -> {

            synchronized (lock2) {
                try {
                    System.out.println("get lock2");
                    Thread.sleep(30000);

                    synchronized (lock1) {
                        System.out.println("get lock1");
                    }

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

        }).start();

    }
}
