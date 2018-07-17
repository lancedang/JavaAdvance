package advance.myextends;

class Father {
	int i;
	int j;
	public Father() {
		// TODO Auto-generated constructor stub
		i = 1;
		j = 2;
	}
}

class Son extends Father {
	int k;
	int i;
	public Son() {
		// TODO Auto-generated constructor stub
		k = 3;
		i = 4;
	}
	
	void show() {
		System.out.println("i = " + i);
		System.out.println("this.i = " + this.i);
		System.out.println("super.i = " + super.i);
	}
	
}
public class ExtendsDemo {

}
