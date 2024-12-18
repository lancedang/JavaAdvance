package concurrent.core2.aqs.locksupport;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.LockSupport;

@Slf4j
public class LockSupportDemo {
    public static void main(String[] args) throws InterruptedException {
        TaskThread a1 = new TaskThread("A");
        Thread a2 = new TaskThread("B");

        a1.start();
        a2.start();

        TimeUnit.SECONDS.sleep(4);
        LockSupport.unpark(a1);
        LockSupport.unpark(a2);
        TimeUnit.SECONDS.sleep(1);
        a1.interrupt();

        TimeUnit.SECONDS.sleep(5);
        LockSupport.unpark(a2);
    }

    public static class TaskThread extends Thread{

        public TaskThread(String name) {
            super(name);
        }

        @Override
        public void run() {
            Thread thread = Thread.currentThread();
            log.info("{} 将要永久阻塞", thread.getName());
            LockSupport.park();
            //这个地方是开发者自己添加检测逻辑，对比sleep()，sleep无需手动执行检测中断状态的代码
            //检测被中断后，代码从这开始继续向下执行，类似wait()被notify后也是从wait()之后的代码开始执行
            if (thread.isInterrupted()) {
                log.info("{} 被thread.interrupt()中断了", thread.getName());
            }else {
                log.info("{} 被LockSupport.unpark(threadName)唤醒了", thread.getName());
            }
        }

    }
}
