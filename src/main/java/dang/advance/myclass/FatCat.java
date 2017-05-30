package dang.advance.myclass;

import java.util.Random;

public class FatCat extends Cat{
	/*static {
		System.out.println("FatCat static");
	}*/
	
	//private Cat cat;
	private Random random;
	private Random random2 = new Random();
	static {
		System.out.println("FatCat static");
	}
	public FatCat() {
		// TODO Auto-generated constructor stub
		//cat = new Cat();
		random = new Random();
		System.out.println("FatCat Constructor");
	}
	
	public static void main(String[] args) {
		//new Cat();
		//System.out.println("............");
		//new FatCat();
		new FatCat();
		//new Cat();
	}
}
