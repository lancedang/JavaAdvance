package threadDesignPattern.threadlocal;

public class MyThread  extends Thread{

    @Override
    public void run() {
        ThreadLocalUtil.print("xss");
        ThreadLocalUtil.close();

    }
}
