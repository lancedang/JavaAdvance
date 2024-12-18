package designPattern.filter.impl;

import designPattern.filter.entity.Person;
import designPattern.filter.infacer.Criteria;

import java.util.List;

public class AndCriteria implements Criteria {
	private Criteria c1;
	private Criteria c2;
	
	public AndCriteria(Criteria c1, Criteria c2) {
		this.c1 = c1;
		this.c2 = c2;
	}
	public List<Person> meetCriteria(List<Person> persons) {
		// TODO Auto-generated method stub
		List<Person> list1 = c1.meetCriteria(persons);
		List<Person> list2 = c2.meetCriteria(list1);
		return list2;
	}

}
