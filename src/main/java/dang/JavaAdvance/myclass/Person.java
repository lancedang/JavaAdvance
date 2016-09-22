package dang.JavaAdvance.myclass;

public class Person {
	private int id;
	private String name;
	
	static {
		System.out.println("This is Person static chunk. ");
	}
	static {
		System.out.println("This is Person static chunk. ");
	}
/*
	public Person() {
		super();
		// TODO Auto-generated constructor stub
		System.out.println("This is person Non-Param Constructor. ");
	}
*/
	Person(){
		
	}
	
	public Person(int id, String name) {
		super();
		this.id = id;
		this.name = name;
		System.out.println("This is person Param Constructor. ");
	}

	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}

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

}
