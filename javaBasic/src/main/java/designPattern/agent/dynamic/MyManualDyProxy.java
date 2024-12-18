package designPattern.agent.dynamic;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

//这个是我手动编写的动态代理类
//按照静态代理方式手写这个Java文件
//动态代理其实就是帮我们把这个Java文件对于的字节码生成好了
public class MyManualDyProxy extends Proxy implements ISell {

    final static Method sellMethod;
    final static Method advertiseMethod;

    static {
        try {
            //通过反射获取ISell接口的sell和advertise方法
            sellMethod = Class.forName("designPattern.agent.dynamic.ISell").getMethod("sell");
            advertiseMethod = Class.forName("designPattern.agent.dynamic.ISell").getMethod("advertise");
        } catch (NoSuchMethodException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }


    protected MyManualDyProxy(InvocationHandler h) {
        super(h);
    }

    @Override
    public void sell() {
        try {
            //借用invocationHandler调用sell方法
            this.h.invoke(this, sellMethod, new Object[]{});
        } catch (Throwable e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void advertise() {
        try {
            this.h.invoke(this, advertiseMethod, new Object[]{});
        } catch (Throwable e) {
            throw new RuntimeException(e);
        }
    }
}
