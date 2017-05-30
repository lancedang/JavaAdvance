package dang.advance.job.polymorp.factory;

import dang.advance.job.polymorp.inter.Shape3;
import dang.advance.job.polymorp.interImpl.Circle3;
import dang.advance.job.polymorp.interImpl.Square3;

public class Shape3Generator {
	public Shape3 next(String str) {
		switch (str) {
		default:
		case "circle":
			return new Circle3();
		case "square":
			return new Square3();
		}
	}
}
