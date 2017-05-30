package dang.advance.job.thread;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class Call {
	public static void main(String[] arg) throws InterruptedException, ExecutionException {
		// TODO Auto-generated method stub
		// 调度服务器,线程库,t可以设定大小
		ExecutorService service = Executors.newFixedThreadPool(2);
		Race tortoise = new Race("老兔子", 1000);
		Race rabbit = new Race("小兔子", 500);
		// 把真实对象丢到代理对象中X
		Future<Integer> result = service.submit(tortoise);
		Future<Integer> result2 = service.submit(rabbit);

		Thread.sleep(2000);
		tortoise.setFlag(false);
		rabbit.setFlag(false);

		int num = result.get();
		int num2 = result2.get();
		service.shutdown();
		service.shutdownNow();
		System.out.println(num);
		System.out.println(num2);
	}
}

/**
 * 声明异常和返回不同类型值
 * 
 * @author Dangdang
 *
 */
class Race implements Callable<Integer> {
	private String name; // 名称
	private long time; // 时间
	private boolean flag = true;

	private int step = 0;

	Race(String name, long time) {
		this.name = name;
		this.time = time;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name
	 *            the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the time
	 */
	public long getTime() {
		return time;
	}

	/**
	 * @param time
	 *            the time to set
	 */
	public void setTime(long time) {
		this.time = time;
	}

	/**
	 * @return the flag
	 */
	public boolean isFlag() {
		return flag;
	}

	/**
	 * @param flag
	 *            the flag to set
	 */
	public void setFlag(boolean flag) {
		this.flag = flag;
	}

	@Override
	public Integer call() throws Exception {
		// TODO Auto-generated method stub
		while (flag) {
			Thread.sleep(1000);
			step++;
		}
		return step;
	}
}
