package dang.JavaAdvance.myclass;

public class FatCat extends Cat{
	/*static {
		System.out.println("FatCat static");
	}*/
	
	private Cat cat = new Cat();
	
	public FatCat() {
		// TODO Auto-generated constructor stub
		System.out.println("FatCat Constructor");
	}
	
	public static void main(String[] args) {
		//new Cat();
		System.out.println("............");
		new FatCat();
	}
}
