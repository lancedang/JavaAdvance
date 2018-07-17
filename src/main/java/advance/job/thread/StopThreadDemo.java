package advance.job.thread;


class Stop extends Thread {
	@Override
	public void run() {
		// TODO Auto-generated method stub
		super.run();
		for (int i = 0; i < 500000; i++) {
			System.out.println("i= " + (i + 1));
		}
	}

}

class Stop2 implements Runnable {
	@Override
	public void run() {
		// TODO Auto-generated method stub
		// super.run();
		for (int i = 0; i < 500000; i++) {
			System.out.println("i= " + i);
		}
	}

}

class MyStopThread3 extends Thread {
	int count = 0;

	@Override
	public void run() {
		// TODO Auto-generated method stub
		super.run();
		while (true) {
			try {
				count++;
				System.out.println("i = " + count);
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO: handle exception
				System.out.println("run InterruptedException catched.");
				e.printStackTrace();
				// break; //break只在 interrupt异常时 用来跳出循环，若是stop线程，都会结束
			}
		}
		// System.out.println("尽管Interrupted，现在进入while循环外 ");
	}
}

class MyStopThread4 extends Thread {
	int count = 0;

	@Override
	public void run() {
		// TODO Auto-generated method stub
		super.run();
		try {
			this.stop();

		} catch (ThreadDeath e) {
			// TODO: handle exception
			System.out.println("进入ThreadDeath异常catch ");
			e.printStackTrace();
		}
	}
}

public class StopThreadDemo {
	public static void demo2(String[] args) {

		try {
			Stop myStopThread = new Stop();
			myStopThread.start();
			Thread.sleep(3000);
			myStopThread.interrupt();
			System.out.println("----------------------是否停止1？= " + myStopThread.isInterrupted());
			System.out.println("----------------------是否停止2？= " + myStopThread.isInterrupted());

		} catch (InterruptedException exception) {
			System.out.println("main catch");
			exception.printStackTrace();
		}
		System.out.println("end!");

	}

	public static void demo1(String[] args) {
		try {
			Stop stop = new Stop();
			stop.start();
			Thread.sleep(2000);
			stop.interrupt();
			System.out.println("是否停止1？ = " + stop.interrupted());
			System.out.println("是否停止2？ = " + stop.interrupted());

			Thread.sleep(4000);
			Thread.currentThread().interrupt();
			System.out.println("Thread.currentThread().interrupt()= " + Thread.interrupted());
			System.out.println("Thread.currentThread().interrupt()= " + Thread.interrupted());

		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			System.out.println("main catch");
			e.printStackTrace();

		}

	}

	public static void demo3() {
		try {
			MyStopThread3 myStopThread3 = new MyStopThread3();
			System.out.println("main begin");
			myStopThread3.start();
			Thread.sleep(5000);
			myStopThread3.interrupt();
			// myStopThread3.stop();
			System.out.println("main end");
		} catch (InterruptedException exception) {
			System.out.println("main interruptedException catched");
			exception.printStackTrace();
		}
	}

	static void demo4() {
		MyStopThread4 myStopThread4 = new MyStopThread4();
		myStopThread4.start();
	}

	public static void main(String[] args) {
		demo4();
	}

}
