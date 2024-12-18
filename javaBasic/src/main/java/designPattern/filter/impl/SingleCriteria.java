package designPattern.filter.impl;

import designPattern.filter.entity.Person;
import designPattern.filter.infacer.Criteria;

import java.util.ArrayList;
import java.util.List;

public class SingleCriteria implements Criteria {

	public List<Person> meetCriteria(List<Person> persons) {
		// TODO Auto-generated method stub
		List<Person> list = new ArrayList<Person>();
		for(Person person : persons) {
			if(person.isSingle()) {
				list.add(person);
			}
		}
		return list;
	}

}
