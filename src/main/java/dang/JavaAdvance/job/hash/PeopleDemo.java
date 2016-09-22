package dang.JavaAdvance.job.hash;

import java.util.HashMap;

public class PeopleDemo {
	public static void main(String[] args) {
		HashMap<People, Integer> map = new HashMap<People, Integer>();
		People people1 = new People("san", 11);
		People people2 = new People("san", 11);
		
		System.out.println("p1 equals p2 " + people1.equals(people2));
		
		People people3 = people1;
		
		map.put(people1, 1);
		map.put(people2, 2);
		//map保存的key不能重复，当存入相同key时，覆盖旧的
		people3.setAge(100);
		map.put(people3, 3);
		System.out.println("size " + map.size());
		
		System.out.println(map.get(new People("san", 11)));
	}
}
