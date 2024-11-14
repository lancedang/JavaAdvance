package threadDesignPattern.threadlocal;

import java.util.concurrent.TimeUnit;

public class Main {
    public static void main(String[] args) {

        new MyThread().start();
        new MyThread().start();
        new MyThread().start();
    }
    public static void main2(String[] args) {
        for (int i = 0; i < 5; i++) {
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            //MyLog.print(i + "");
        }
        //close不执行，不会flush，文件中没有内容
        //MyLog.close();
    }
}
