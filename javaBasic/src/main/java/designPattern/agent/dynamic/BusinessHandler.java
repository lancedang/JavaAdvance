package designPattern.agent.dynamic;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;


/**
 * 最终的目的还是： 通过 代理类 调用与target 相同方法达到最终目的
 * 静态代理类有个环节：就是对target 方法进行加工, 如前后打日志
 * 现在问题是：如何将针对 target, 对target 方法添加前后逻辑, 并且还得移交到target 的真实方法(逻辑),  且在没有自定义 代理类的情形下
 * 所以  JDK 引入了新的中间类 InvocationHandler, InvocationHandler 的目的就是：绑定实际的真实 target对象
 */

public class BusinessHandler implements InvocationHandler {

    private Object vendor;
    private Object target; // 相比第一行更通用了,
    // 因为此刻不知道委托类具体是谁，故不能明确定义成Vendor,由多态可知，定义成Object

    public BusinessHandler(Object vendor) {
        this.vendor = vendor;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("before");

        //借此，可看到，我们不管目的真实类/被代理类到底有多少个（成千上万个）方法，都无所谓
        //我们只需要在此加上前后的增强日志逻辑即可，（如此所有方法1w个都会加上日志增强）
        Object result = method.invoke(vendor, args); // 直观上理解，method.invoke()就是执行Method方法，
        // 这个方法隶属vendor类(param1)代理模式种的委托类，且这个方法所需参数为param2
        System.out.println("after");

        System.out.println("result " + result);

        return result;
    }

}
