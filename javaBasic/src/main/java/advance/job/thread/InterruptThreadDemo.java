package advance.job.thread;


class MyInterruptThread extends Thread {
	@Override
	public void run() {
		// TODO Auto-generated method stub
		super.run();
		try {
			for (int i = 0; i < 500000; i++) {
				if (Thread.interrupted()) { // 判断自己interruptFlag是否为TRUE
					System.out.println("I'm interrupted, so I quit!");
					throw new InterruptedException();
				}
				System.out.println("i = " + (i + 1));
			}

			System.out.println("interrupted 并且throw InterruptedException后, 在for循环之外"); // throw异常后就进入catch了，词句轮不到执行
		} catch (InterruptedException exception) {

			System.out.println("进入 InterruptedException");
			exception.printStackTrace();
		}
	}
}

class MyInterruptThread2 extends Thread {
	@Override
	public void run() {
		// TODO Auto-generated method stub
		try {
			super.run();
			System.out.println("run begin");
			Thread.sleep(20000);
			System.out.println("run end");
		} catch (InterruptedException e) {
			// TODO: handle exception
			System.out.println("在沉睡中被停止，进入catch, this.isInterrupted() = " + this.isInterrupted());
			System.out.println("在沉睡中被停止，进入catch，现在我醒来后能做很多事，好多好多 ");

			e.printStackTrace();
		}
	}
}

class MyInterruptThread3 extends Thread {
	@Override
	public void run() {
		// TODO Auto-generated method stub
		super.run();
		try {
			System.out.println("run begin");
			for (int i = 0; i < 1000000; i++) {
				System.out.println("i = " + (i + 1));
				System.out.println("this.isInterrupted() = " + this.isInterrupted());
			}
			System.out.println("for 循环结束，本线程开始睡觉");
			Thread.sleep(20000);
			System.out.println("本线程休息20秒之后。。。");
		} catch (InterruptedException e) {
			System.out.println(
					"InterruptedException catched,进入catch, 此刻， this.isInterrupted() = " + this.isInterrupted());
			e.printStackTrace();
			// TODO: handle exception
		}

	}
}

public class InterruptThreadDemo {
	public static void demo1() {
		MyInterruptThread myInterruptThread = new MyInterruptThread();
		myInterruptThread.start();
		try {
			Thread.sleep(200);
			myInterruptThread.interrupt();// main发命令了，我要中断你
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			System.out.println("Main Catch!");
			e.printStackTrace();
		}
		System.out.println("main end");
	}

	public static void demo2() {
		try {
			MyInterruptThread2 myInterruptThread2 = new MyInterruptThread2();
			System.out.println("main begin");
			myInterruptThread2.start();
			Thread.sleep(10000);
			myInterruptThread2.interrupt();
			System.out.println("main end");
		} catch (InterruptedException e) {
			// TODO: handle exception
			System.out.println("main interruptedException catched");
			e.printStackTrace();
		}

	}

	public static void demo3() {
		try {
			MyInterruptThread3 myInterruptThread3 = new MyInterruptThread3();
			System.out.println("main begin");
			myInterruptThread3.start();
			Thread.sleep(4000);
			myInterruptThread3.interrupt();

			System.out.println("main end");
		} catch (InterruptedException e) {
			// TODO: handle exception
			System.out.println("main interruptedException Catched");
			e.printStackTrace();
		}

	}

	public static void main(String[] args) {
		demo3();
	}
}
