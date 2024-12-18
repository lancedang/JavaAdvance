package designPattern.agent.dynamic;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.lang.reflect.UndeclaredThrowableException;

/**
 * 模拟JDK动态代理生成的代理类
 * 实现ISell接口是为了用反射找到实现的所有方法
 * 继承Proxy是为了利用父类的InvocationHandler调用 invoke方法，该方法绑定target委托类
 * 
 * @author Dangdang
 *
 */
public class MyDyProxy extends Proxy implements ISell {

	private static Method M1;
	private static Method M2;
	private static Method M3;
	private static Method M4;
	private static Method M5;

	static {
		try {
			// M1 = ISell.class.getMethod("equals", new Class[]{});
			// M2 = ISell.class.getMethod("hashCode", new Class[]{});

			M1 = Class.forName("java.lang.Object").getMethod("toString", new Class[] {});
			M2 = Class.forName("java.lang.Object").getMethod("hashCode", new Class[] {});
			M3 = Class.forName("java.lang.Object").getMethod("equals",
					new Class[] { Class.forName("java.lang.Object") });

			M4 = Class.forName("designPattern.agent.dynamic.ISell").getMethod("sell", new Class[] {});
			M5 = Class.forName("designPattern.agent.dynamic.ISell").getMethod("ad", new Class[] {});

		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	protected MyDyProxy(InvocationHandler h) {
		super(h);
	}

	@Override
	public void sell() {
		try {
			this.h.invoke(this, M4, new Object[] {});
		} catch (Throwable e) {
			e.printStackTrace();
		}
	}

	@Override
	public void advertise() {
		try {
			this.h.invoke(this, M5, new Object[] {});
		} catch (Throwable e) {
			e.printStackTrace();
		}
	}

	public int hashCode() {
		try {
			return (int) this.h.invoke(this, M2, null);
		} catch (Throwable e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new UndeclaredThrowableException(e);
		}
	}

	public boolean equals(Object object) {
		try {
			return (boolean) this.h.invoke(this, M1, new Object[] {});
		} catch (Throwable e) {
			e.printStackTrace();
			throw new UndeclaredThrowableException(e);
		}
	}

	public String toString() {
		try {
			return (String)this.h.invoke(this, M3, new Object[] {});
		} catch (Throwable e) {
			e.printStackTrace();
			throw new UndeclaredThrowableException(e);
		}
	}
}
