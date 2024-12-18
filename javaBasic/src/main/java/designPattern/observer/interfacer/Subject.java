package designPattern.observer.interfacer;

public interface Subject {
	void registerOberver(AbObserver abObserver);
	void removeOberver(AbObserver abObserver);
	
	void notifyAllObervers();
}
