package designPattern.agent.staticc;

import designPattern.agent.SimpleSellor;

public class StaticAgentDemo {
	public static void main(String[] args) {

		//先准备好代理类
		MyStaticProxy myStaticProxy = new MyStaticProxy(new SimpleSellor());

		//与外界交互, 需要与直接访问 target 的行为(方法)保持一致,  一种方式是：代理类与 target 类实现了共同的接口
        //代理类的方法与 target 保持了统一
		myStaticProxy.sell();
		myStaticProxy.advertise();
	}
}
