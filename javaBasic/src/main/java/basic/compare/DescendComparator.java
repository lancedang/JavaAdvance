package basic.compare;

import java.util.Comparator;

public class DescendComparator implements Comparator<Person> {

	public int compare(Person o1, Person o2) {
		// TODO Auto-generated method stub
		return o2.age - o1.age;
	}

}
