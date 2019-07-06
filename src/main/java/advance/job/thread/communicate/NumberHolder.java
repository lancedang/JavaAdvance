package advance.job.thread.communicate;

/**
 * 线程共享类，共享类封装了线程共同操作的数据信息，如
 * 库存，且封装对该共享信息的业务操作，如增删改查，面临多线程环境，需要对该共享资源互斥操作，所以其业务方法需要进行同步
 * 
 * @author Dangdang
 *
 */
public class NumberHolder {
	private int number;

	public synchronized void increase() {
		if (number != 0) {
			try {
				this.wait();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		number++;
		System.out.println(Thread.currentThread().getName() + " num " + number);
		this.notify();
	}

	public synchronized void decrease() {
		if (number == 0) {
			try {
				this.wait();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		number--;
		System.out.println(Thread.currentThread().getName() + " num " + number);
		notify();
	}

}
