package dang.advance.mythis;

public class PersonDemo {
	private int max = 0;

	public void say(int max) {
		System.out.println("类属型 max = " + this.max); // this表示 当前调用该方法 的那个对象
		System.out.println("方法内 局部属性 max = " + max);
	}

	public PersonDemo increment() {
		max++;
		return this; //返回当前调用该方法的对象
	}

	public void print() {
		System.out.println("max is " + max);
	}

	public static void main(String[] args) {
		PersonDemo demo = new PersonDemo();
		demo.increment().increment().increment().print();
	}

}
