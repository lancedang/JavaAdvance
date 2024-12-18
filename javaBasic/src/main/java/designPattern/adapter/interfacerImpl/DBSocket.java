package designPattern.adapter.interfacerImpl;

import designPattern.adapter.interfacer.DBSocketInterface;

/**
 * 德国插座
 * @author Dangdang
 *
 */
public class DBSocket implements DBSocketInterface {

	public void powerWithTwoRound() {
		// TODO Auto-generated method stub
		System.out.println("This is Germany socket.");
	}

}
