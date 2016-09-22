package dang.JavaAdvance.job.polymorp.interImpl;

import dang.JavaAdvance.job.polymorp.inter.Shape3;

public class Circle3 extends Shape3 {

	public void show() {
		System.out.println("Circle3 show.");
	}
	
	//父类的say方法为private
	public void say() {
		System.out.println("Circle3 say.");
	}

}
