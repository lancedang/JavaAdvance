package dang.advance.job.thread;

class MyThread_1 extends Thread {

	public MyThread_1() {
		System.out.println("Constructor method begin---------- ");
		System.out.println(
				"Now in Constructor method, Thread.currentThread().getName() : " + Thread.currentThread().getName()
						+ ", Thread.currentThread().getId(): " + Thread.currentThread().getId());
		System.out.println(
				"Now in Constructor method, this.currentThread().getName() : " + this.currentThread().getName());
		System.out.println(
				"Now in Constructor method, this.getName(): " + this.getName() + ", this.getId(): " + this.getId());
		// System.out.println(
		// "Now in Constructor method, this.isAlive() : " + this.isAlive());
		System.out.println("Constructor method end---------- ");
	}

	public void run() {
		System.out.println("Run method begin---------- ");
		System.out.println("Now in run method, Thread.currentThread().getName() : " + Thread.currentThread().getName()
				+ "; Thread.currentThread().getId(): " + Thread.currentThread().getId());
		System.out.println("Now in run method, this.currentThread().getName() : " + this.currentThread().getName());
		System.out.println("Now in run method, this.getName(): " + this.getName() + ", this.getId(): " + this.getId());
		// System.out.println(
		// "Now in run method, this.isAlive() : " + this.isAlive());
		System.out.println("Run method end---------- ");
	}
}

public class CurrentThreadDemoPlus {
	public static void main(String[] args) {
		MyThread_1 myThread = new MyThread_1();

		myThread.setName("myThread");
		System.out.println("myThread.isAlive() " + myThread.isAlive());
		Thread t1 = new Thread(myThread, "t1");
		// Thread t2 = new Thread(myThread, "t2");
		// Thread t3 = new Thread(myThread, "t3");

		// myThread.start();

		System.out.println("myThread.isAlive() " + myThread.isAlive());
		t1.start();
		// t3.start();

	}
}