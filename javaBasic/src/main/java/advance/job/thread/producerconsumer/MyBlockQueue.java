package advance.job.thread.producerconsumer;

import java.util.LinkedList;
import java.util.List;

/**
 * 共享类，互斥资源
 * 
 * @author Dangdang
 *
 * @param <T>
 */
public class MyBlockQueue<T> {
	private List<T> queue = new LinkedList<>();
	private int capacity = 10;

	public MyBlockQueue(int size) {
		this.capacity = size;
	}

	public void enterQueue(T t) {
		if (queue.size() == capacity) {
			try {
				this.wait();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if (queue.size() == 0) {
			this.notifyAll();
		}
		queue.add(t);
	}

	public T exitQueue() {
		if (queue.size() == 0) {
			try {
				this.wait();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if (queue.size() == capacity) {
			this.notifyAll();
		}
		return queue.remove(queue.size() - 1);
	}

}
