package dang.advance.job.thread.communicate;

public class DecreaseThread extends Thread {
	private NumberHolder holder;

	public DecreaseThread(NumberHolder holder) {
		this.holder = holder;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		for (int i = 0; i < 20; i++) {
			try {
				Thread.sleep((long) Math.random() * 1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			holder.decrease();
		}
	}

}
