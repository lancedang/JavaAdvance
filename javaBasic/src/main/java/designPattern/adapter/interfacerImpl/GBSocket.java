package designPattern.adapter.interfacerImpl;

import designPattern.adapter.interfacer.GBSocketInterface;

/**
 * 国产插座
 * @author Dangdang
 *
 */
public class GBSocket implements GBSocketInterface {

	public void powerWithThreeFlat() {
		// TODO Auto-generated method stub
		System.out.println("This is China Socket.");
	}

}
