package advance.job.thread;

/**
 * 然而，当一个线程访问object的一个synchronized(this)同步代码块时，另一个线程仍然可以访问该object中的非synchronized
 * (this)同步代码块。
 * 
 * @author Dangdang
 *
 */
public class My2Thread {
	/**
	 * 同步代码块
	 */
	public void show1() {
		synchronized (this) {
			int i = 5;
			while (i-- > 0) {
				System.out.println(Thread.currentThread().getName() + " loop " + i);
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}

	/**
	 * 非同步代码块
	 */
	public void show2() {
		synchronized(this) {
			int i = 5;
			while (i-- > 0) {
				System.out.println(Thread.currentThread().getName() + " loop " + i);
				try {
					Thread.sleep(1000);
				} catch (InterruptedException interruptedException) {
					interruptedException.printStackTrace();
				}
			}
		}
	}
}
