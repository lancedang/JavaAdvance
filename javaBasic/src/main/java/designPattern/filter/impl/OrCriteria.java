package designPattern.filter.impl;

import designPattern.filter.entity.Person;
import designPattern.filter.infacer.Criteria;

import java.util.List;

/**
 * 
 * @author Dangdang
 * meet two criteria's results
 *
 */
public class OrCriteria implements Criteria {

	private Criteria c1;
	private Criteria c2;
	
	public OrCriteria(Criteria c1, Criteria c2) {
		this.c1 = c1;
		this.c2 = c2;
	}

	
	public List<Person> meetCriteria(List<Person> persons) {
		// TODO Auto-generated method stub
		List<Person> list1 = c1.meetCriteria(persons);
		List<Person> list2 = c2.meetCriteria(persons);
		
		for(int i=0; i<list1.size(); i++) {
			Person p = list1.get(i);
			if(!list2.contains(p)) {
				list2.add(p);
			}
		}
		
		return list2;
	}

}
