package dang.advance.job.thread;

import javax.persistence.criteria.Join;

public class JoinDemo extends Thread{
	@Override
	public void run() {
		// TODO Auto-generated method stub
		for (int i=0; i<100; i++) {
			System.out.println("thread i " + i);
		}
	}
	
	public static void main(String[] args) {
		JoinDemo joinDemo = new JoinDemo();
		new Thread(joinDemo).start();
		
		for (int i = 0; i<100; i++) {
			if (i == 50 ) {
				try {
					joinDemo.join();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
			System.out.println("main i " + i);
		}
		
	}
	
}
