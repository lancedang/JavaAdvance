package dang.JavaAdvance.job.thread;

/**
 * 尤其关键的是，当一个线程访问object的一个synchronized(this)同步代码块时，其他线程对object中所有其它synchronized(
 * this)同步代码块的访问将被阻塞。
 * 
 * @author Dangdang
 *
 */
public class My3Thread {
	/**
	 * 同步代码块
	 */
	public void show1() {
		synchronized (this) {
			int i = 5;
			while (i-- > 0) {
				System.out.println(Thread.currentThread().getName() + " loop " + i);
				try {
					Thread.sleep(500);
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
		synchronized (this) {
			int i = 5;
			while (i-- > 0) {
				System.out.println(Thread.currentThread().getName() + " loop " + i);
				try {
					Thread.sleep(500);
				} catch (InterruptedException interruptedException) {
					interruptedException.printStackTrace();
				}
			}
		}
	}

	/**
	 * 同synchronized(this)代码块
	 */
	public synchronized void show3() {
		int i = 0;
		while (i++ < 5) {
			System.out.println(Thread.currentThread().getName() + " loop " + i);
			try {
				Thread.sleep(500);
			} catch (InterruptedException interruptedException) {
				interruptedException.printStackTrace();
			}
		}
	}

}
