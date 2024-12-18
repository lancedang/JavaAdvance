package designPattern.filter.impl;

import designPattern.filter.entity.Person;
import designPattern.filter.infacer.Criteria;

import java.util.ArrayList;
import java.util.List;

public class MaleCriteria implements Criteria {
	
	/*
	 * public <K> K[] meetCriteria(K[] kes) { // TODO Auto-generated method stub
	 * return null; }
	 */

	public List<Person> meetCriteria(List<Person> persons) {
		// TODO Auto-generated method stub
		List<Person> males = new ArrayList<Person>();
		
		for(Person p : persons) {
			if(p.getSex().equalsIgnoreCase("male")) {
				males.add(p);
			}
		}
		
		return males;
	}

}
