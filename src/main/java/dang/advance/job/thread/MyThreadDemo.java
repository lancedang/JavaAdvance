package dang.advance.job.thread;

public class MyThreadDemo {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		MyThread thread = new MyThread(); // 实例化一个实现Runnable 接口的类
		MyThread thread2 = new MyThread(); // synchronized必须是同一个对象的某段代码段
		Thread t1 = new Thread(thread, "MyThread-1"); // 构建两个线程
		Thread t2 = new Thread(thread2, "MyThread-2"); // 1)把synchronized
														// 标志去掉试试；2) 领取一个对象试试
		t1.start();
		t2.start();

	}

}
