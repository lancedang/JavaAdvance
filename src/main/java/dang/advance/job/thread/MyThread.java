package dang.advance.job.thread;

/**
 * 当两个并发线程访问同一个对象object中的这个synchronized(this)同步代码块时，一个时间内只能有一个线程得到执行。
 * 另一个线程必须等待当前线程执行完这个代码块以后才能执行该代码块。
 * 
 * @author Dangdang
 *
 */
public class MyThread implements Runnable {

	public void run() {
		
		// TODO Auto-generated method stub
		 synchronized(this){
			for (int i = 0; i < 5; i++) {
				try {
					Thread.currentThread().sleep(1000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				System.out.println(Thread.currentThread().getName() + " synchronized loop " + i);
			}
		}
	}

}
