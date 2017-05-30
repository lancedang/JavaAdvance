package dang.advance.job.thread;

public class Web12306Run implements Runnable {
	private int num = 40;

	public void run() {

		while (true) {
			if (num <= 0) {
				return;
			}
			System.out.println(Thread.currentThread().getName() + " 抢到了 " + num--);
		}
	}

	public static void main(String[] args) {
		// 只有一个12306
		Web12306Run web12306 = new Web12306Run();
		// 三个黄牛即代理类
		Thread proxy1 = new Thread(web12306, "one");
		Thread proxy2 = new Thread(web12306, "two");
		Thread proxy3 = new Thread(web12306, "three");

		proxy1.start();
		proxy2.start();
		proxy3.start();
	}

}
