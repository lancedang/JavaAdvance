package designPattern.simfactory;

import designPattern.simfactory.intfacer.Shape;
import designPattern.simfactory.intfacerImpl.Circle;
import designPattern.simfactory.intfacerImpl.Retangle;
import designPattern.simfactory.intfacerImpl.Square;

public class ShapeFactory {

	public static Shape getShape(String type) {
		switch (type) {
		case "Rectangle":
			return new Retangle();
		case "Circle":
			return new Circle();
		case "Square":
			return new Square();
		default:
			return null;
		}
	}
}
