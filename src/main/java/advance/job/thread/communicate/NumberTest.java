package advance.job.thread.communicate;

public class NumberTest {
	public static void main(String[] args) {
		NumberHolder holder = new NumberHolder();

		Thread thread1 = new IncreaseThread(holder);
		thread1.setName("thread1");
		Thread thread2 = new DecreaseThread(holder);
		thread2.setName("thread2");

		thread1.start();
		thread2.start();
	}
}
