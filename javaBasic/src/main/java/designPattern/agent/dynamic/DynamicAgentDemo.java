package designPattern.agent.dynamic;

import designPattern.agent.SimpleSellor;

import java.lang.reflect.Proxy;

public class DynamicAgentDemo {
    public static void main(String[] args) {

        BusinessHandler handler = new BusinessHandler(new SimpleSellor());
        Class<?>[] iClasses = {ISell.class};

        //动态代理的本质就是：你不用在自定义代理类(就像自己办工厂生产东西一样), 不需要亲力亲为
        //但达到这种结果是需要代价的：
        //jdk工具类Proxy帮你生成代理类，当然你需要传递相应规约的参数进去
        //这个ISell即帮我们生成的代理类
        ISell dyAgent = (ISell) Proxy.newProxyInstance(ISell.class.getClassLoader(), iClasses, handler);

        System.getProperties().put("sun.misc.ProxyGenerator.saveGeneratedFiles", "true");

        //如果不行可以试下下面这行代码
        System.getProperties().put("jdk.proxy.ProxyGenerator.saveGeneratedFiles", "true");

        //使用代理类执行增强逻辑
        dyAgent.sell();
        dyAgent.advertise();

    }
}
