package designPattern.agent;

import designPattern.agent.dynamic.ISell;

//目的类/被代理类/真实角色
public class SimpleSellor implements ISell {

	@Override
	//父接口，可能有上百个方法，需要实现
	public void sell() {
		System.out.println("Vendor Sell. ");
	}

	@Override
	public void advertise() {
		System.out.println("Vendor Advertise. ");
	}

}
