package advance.job.thread.waitnotify;

import java.util.concurrent.TimeoutException;

public class WaitNotifyTimeOut {
    public static void main(String[] args) throws InterruptedException, TimeoutException {
        System.out.println("begin");
        new MyThread(10000).execute();
    }
}

class MyThread extends Thread{
    private final long timeout;
    private volatile boolean ready = false;

    public MyThread(long timeout) {
        this.timeout = timeout;
    }

    public void setReady(boolean ready) {
        this.ready = ready;
    }

    @Override
    public void run() {
        //execute();
    }

    public synchronized void execute() throws InterruptedException, TimeoutException {
        long start = System.currentTimeMillis();
        System.out.println("start = " + start);
        while (!ready) {

            long now = System.currentTimeMillis();

            long rest = timeout - (now - start);

            if(rest < 0){
                throw new TimeoutException("timeout = "+ timeout + ", now - start=" + (now - start));
            }
            System.out.println("thread= " + Thread.currentThread()+ ", before wait rest=" + rest);
            wait(rest);
            System.out.println("thread= " + Thread.currentThread()+ ", after wait rest=" + rest);
        }
    }
}