package designPattern.filter.entity;

public class Person {
	private String name;
	private String sex;
	private int age;

	private boolean single;

	public boolean isSingle() {
		return single;
	}

	public void setSingle(boolean single) {
		this.single = single;
	}

	public Person(String name, String sex, int age) {
		//super();
		this.name = name;
		this.sex = sex;
		this.age = age;
	}
	
	public Person(String name, String sex, int age, boolean single) {
		this(name, sex, age);
		this.single = single;
	}

	
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

}
