package dang.advance.job.thread;

class SynchronizedObject2 {
	synchronized public void printString() {
		if (Thread.currentThread().getName().equals("a")) {
			System.out.println("Thread a will suspend forever.");
			Thread.currentThread().suspend();
		}
	}
}

class MySynchronizedThread2 extends Thread {
	SynchronizedObject2 SynchronizedObject2 = new SynchronizedObject2();

	@Override
	public void run() {
		// TODO Auto-generated method stub
		super.run();
		SynchronizedObject2.printString();
	}
}



public class SuspendThreadDemo2 {
	public static void main(String[] args) {
		MySynchronizedThread2 mySynchronizedThread2 = new MySynchronizedThread2();
		Thread t1 = new Thread(mySynchronizedThread2, "a");
		Thread t2 = new Thread(mySynchronizedThread2, "b");
		t1.start();
		t2.start();
		t1.resume();

	}
}
