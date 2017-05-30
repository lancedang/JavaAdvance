package dang.advance.job.thread;

public class Rabbit extends Thread	{
	public void run() {
		//线程体
		for (int i = 0; i< 10000; i++	 )
		{
			System.out.println("Rabbit run " + i + " 步");
		}
	}

}
class Tortoise extends Thread{
	public void run() {
		//线程体
		for (int i = 0; i< 10000; i++	 )
		{
			System.out.println("Tortoise run " + i + " 步");
		}
	}
}
