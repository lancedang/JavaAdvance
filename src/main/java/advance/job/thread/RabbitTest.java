package advance.job.thread;

/**
 * 模拟龟兔赛跑 1. 创建线程继承Thread，重写run方法
 * 
 * @author Dangdang
 *
 */
public class RabbitTest {
	public static void main(String[] args) {
		// 在main线程中创建另一个线程，新建状态
		Thread rabbit = new Rabbit();
		Thread tortoise = new Tortoise();
		// 启动线程，等待CPU调度，不一定该新线程立刻执行，Rabbit无start方法，继承自Thread父类，加入该线程到父类线程Group
		rabbit.start();
		tortoise.start();
		for (int i = 0; i < 10000; i++) {
			System.out.println("Main run " + i + " 步");
		}
	}
}
