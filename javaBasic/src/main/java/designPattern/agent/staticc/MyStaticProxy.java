package designPattern.agent.staticc;

import designPattern.agent.SimpleSellor;

/**
 * 代理类, 目的对外界屏蔽真实对象, 或在真实对象出手前做些其他逻辑, 如判断、审核, 比如 宋喆代理宝宝做很多事
 * 但代理最终还得让 target 出面完成一些事
 * 而外界访问时不能直接与 target 通话, 是通过代理类来交流的
 */
public class MyStaticProxy implements ISell {

	//target 对象, 王宝强
	private SimpleSellor vendor;

	public MyStaticProxy(SimpleSellor vendor) {
		this.vendor = vendor;
	}

	//代理替 target 做的事, 在宝宝行事前加了日志输出
	//父接口，可能有上百个方法，需要实现，
	//那这里在增加代理逻辑时，就需要把所有的方法成千上万个都加上代理逻辑，太麻烦了，
	@Override
	public void sell() {
		System.out.println("Before sell");
		vendor.sell();
		System.out.println("After sell");

	}

	@Override
	public void advertise() {
		// TODO Auto-generated method stub
		System.out.println("Before advertise");
		vendor.advertise();
		System.out.println("After advertise");
	}

}
