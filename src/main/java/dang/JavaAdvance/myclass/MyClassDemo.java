package dang.JavaAdvance.myclass;

import java.sql.DriverManager;
import java.sql.SQLException;

import com.mysql.jdbc.Driver;

public class MyClassDemo {
	public static void main(String[] args)
			throws ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException {
		// Person person = new Person(1, "Dd");
		// Person person;
		//Class<?> class1 = Class.forName("dang.JavaAdvance.myclass.Person");
		//new Person();
		//Person person = (Person) class1.newInstance();
		MyClassDemo demo = new MyClassDemo();
		System.out.println(demo.getClass().getClassLoader());
		//Class.forName("dang.JavaAdvance.myclass.Person", true, demo.getClass().getClassLoader());
		Class.forName("com.mysql.jdbc.Driver").newInstance();
		Driver driver = new Driver();
		DriverManager.getConnection("");
		
	}
}
