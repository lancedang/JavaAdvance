package designPattern.observer.interfacerImpl;

import designPattern.observer.interfacer.AbObserver;
import designPattern.observer.interfacer.Subject;

import java.util.ArrayList;

public class Hunter implements Subject{

	private ArrayList<AbObserver> observers;
	private ArrayList<String> jobs;
	
	public Hunter() {
		//super();
		this.observers = new ArrayList<AbObserver>();
		this.jobs = new ArrayList<String>();
	}

	public void registerOberver(AbObserver abObserver) {
		// TODO Auto-generated method stub
		observers.add(abObserver);
	}

	public void removeOberver(AbObserver abObserver) {
		// TODO Auto-generated method stub
		observers.remove(abObserver);
	}

	public void notifyAllObervers() {
		// TODO Auto-generated method stub
		for(AbObserver a : observers) {
			System.out.println("现在有工作了,可以投简历了");
		}
	}

}
