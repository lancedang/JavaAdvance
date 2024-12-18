package designPattern.filter.infacer;

import designPattern.filter.entity.Person;

import java.util.List;

public interface Criteria {
	//public <K> K[] meetCriteria(K[] kes);
	public List<Person> meetCriteria(List<Person> persons); 
}
