package basic.compare;

public class Person implements Comparable<Person> {
	String name;
	int age;

	public Person(String name, int age) {
		// TODO Auto-generated constructor stub
		this.age = age;
		this.name = name;
	}

	public int compareTo(Person o) {
		// TODO Auto-generated method stub
		return name.compareTo(o.name);
	}

}
