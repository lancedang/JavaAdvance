package advance.reflect;

public class User {
	public int age;
	private String name;

	public User() {
		// TODO Auto-generated constructor stub
	}
	
	public User(String name) {
		// TODO Auto-generated constructor stub
		this.name = name;
	}
	
	public User(String name, int age) {
		this.name = name;
		this.age = age;
	}

	public void show1() {
		System.out.println("in show1 method, name = " + name);
	}

	public void show2(String age) {
		System.out.println("in show2 method, name = " + name + "; age = " + age);
	}
	public void show3(String p1, int p2) {
		System.out.println("in show3 method, param1 = " + p1 + "; param2 = " + p2);
	}
	private void show4() {
		System.out.println("in show4 method, private method ");
	}
}
