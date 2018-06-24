package advance.job.thread;

class MySuspendThread extends Thread {
	private int count = 0;

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		super.run();
		while (true) {
			count++;
		}
		// System.out.println("count = " + count);
	}
}

class MySuspendThreadPlus extends Thread {
	private int count = 0;

	@Override
	public void run() {
		// TODO Auto-generated method stub
		super.run();
		while (true) {
			count++;
			System.out.println("count = " + count);
		}
	}
}

public class SuspendThreadDemo {

	public static void main(String[] args) {
		demo2();
	}

	static void demo2() {
		MySuspendThreadPlus mySuspendThreadPlus = new MySuspendThreadPlus();
		mySuspendThreadPlus.start();
		try {
			Thread.sleep(4000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		mySuspendThreadPlus.suspend(); //suspend 是由外界(main线程)发起的，Suspend 未解除，main后续代码不执行，一直悬着
		System.out.println("main end");
	}

	public static void demo1() {
		try {
			MySuspendThread mySuspendThread = new MySuspendThread();
			mySuspendThread.start();
			Thread.sleep(200);
			//
			mySuspendThread.suspend();
			System.out.println("A-A: suspended, count = " + mySuspendThread.getCount() + ", now resume thread");
			Thread.sleep(400);
			System.out.println("A-A: count = " + mySuspendThread.getCount());
			mySuspendThread.resume();
			Thread.sleep(400);
			mySuspendThread.suspend();
			System.out.println(
					"B-B: suspended twice times, count = " + mySuspendThread.getCount() + ", now resume thread");
			Thread.sleep(200);
			System.out.println("B-B: resume, count = " + mySuspendThread.getCount());
			mySuspendThread.resume();

		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
