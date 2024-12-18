package designPattern.simfactory;
import designPattern.simfactory.intfacer.Shape;

public class SimFacDemo {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Shape s1 = ShapeFactory.getShape("Rectangle");
		Shape s2 = ShapeFactory.getShape("Circle");
		Shape s3 = ShapeFactory.getShape("Square");
		
		s1.showShpe();
		s2.showShpe();
		s3.showShpe();
	}

}
