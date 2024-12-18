package designPattern.filter.impl;

import designPattern.filter.entity.Person;
import designPattern.filter.infacer.Criteria;

import java.util.ArrayList;
import java.util.List;

public class FemaleCriteria implements Criteria {

	public List<Person> meetCriteria(List<Person> persons) {
		// TODO Auto-generated method stub
		List<Person> females = new ArrayList<Person>();
		
		for(Person p : persons) {
			if(p.getSex().equalsIgnoreCase("female")) {
				females.add(p);
			}
		}
		
		return females;
	}

}
