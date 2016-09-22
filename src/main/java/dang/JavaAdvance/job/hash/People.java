package dang.JavaAdvance.job.hash;

public class People {
	String name;
	int age;

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name
	 *            the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the age
	 */
	public int getAge() {
		return age;
	}

	/**
	 * @param age
	 *            the age to set
	 */
	public void setAge(int age) {
		this.age = age;
	}

	public People(String name, int age) {
		super();
		this.name = name;
		this.age = age;
	}

	/**
	 * 重写equals方法，表示只要两个人姓名，年龄相同则视为同一人 (non-Javadoc)
	 * 
	 * @see java.lang.Object#equals(java.lang.Object)
	 **/
	@Override
	public boolean equals(Object obj) {
		// TODO Auto-generated method stub
		return name.equals(((People) obj).name) && (age == ((People) obj).age);
	}
	
	@Override
	public int hashCode() {
		return this.name.hashCode() * 37 + age;
	}

}
