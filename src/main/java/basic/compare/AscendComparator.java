package basic.compare;

import java.util.Comparator;

public class AscendComparator implements Comparator<Person> {
	
	public int compare(Person o1, Person o2) {
		return o1.age - o2.age;
	}

}
