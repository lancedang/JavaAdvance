package basic.compare;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class CompareTest {
	public static void main(String[] args) {

		Person[] persons = { new Person("hig", 11), 
				new Person("def", 12), 
				new Person("abc", 13) };

		List<Person> list = new ArrayList<Person>();
		list.add(new Person("hig", 12));
		list.add(new Person("def", 13));
		list.add(new Person("abc", 14));
		// 原顺序
		for (Person p : list)
			System.out.print(p.name + " ");
		// 按Comparable的name排序后
		Collections.sort(list);
		for (Person p : list)
			System.out.print(p.name + " ");
		// 按Comparator 的age排序后
		Collections.sort(list, new DescendComparator());
		for (Person p : list)
			System.out.print(p.age + " ");

		Arrays.sort(persons);
		Arrays.sort(persons, new DescendComparator());
		for (Person p : persons)
			System.out.println(p.age + " ");
	}
}
