package dang.advance.job.thread;

public class My3ThreadDemo {
	public static void main(String[] args) {
		final My3Thread my3Thread = new My3Thread();
		Thread thread1 = new Thread(new Runnable() {

			public void run() {
				// TODO Auto-generated method stub
				my3Thread.show1();
			}
		}, "MyThread-1");

		Thread thread2 = new Thread(new Runnable() {

			public void run() {
				// TODO Auto-generated method stub
				my3Thread.show2();
			}
		}, "MyThread-2");

		Thread thread3 = new Thread(new Runnable() {

			public void run() {
				// TODO Auto-generated method stub
				my3Thread.show3();
			}
		}, "MyThread-3");

		thread1.start();
		thread2.start();
		thread3.start();

	}
}
