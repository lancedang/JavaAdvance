package advance.job.thread;

public class My2ThreadDemo {
	public static void main(String[] args) {

		final My2Thread my2Thread = new My2Thread();

		Thread thread1 = new Thread(new Runnable() {

			public void run() {
				// TODO Auto-generated method stub
				my2Thread.show1();
			}
		}, "MyThread-1");
		Thread thread2 = new Thread(new Runnable() {

			public void run() {
				// TODO Auto-generated method stub
				my2Thread.show2();	//把show2改成show1试试
			}
		}, "MyThread-2");
		
		Thread thread3 = new Thread(new Runnable() {
			
			public void run() {
				// TODO Auto-generated method stub
				
			}
		});

		thread1.start(); // thrad1执行的是 synchronized 同步代码块, thread1
							// 执行该代码时，不允许其它线程染指
		thread2.start(); // thrad2执行的是 其它 非同步代码块，验证thread2 并非非得等到 thread1
							// 把同步代码块执行完，才张罗自己的 代码块，无关

	}
}
