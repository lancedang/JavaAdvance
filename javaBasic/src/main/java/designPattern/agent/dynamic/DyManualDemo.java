package designPattern.agent.dynamic;

import designPattern.agent.SimpleSellor;

public class DyManualDemo {
    public static void main(String[] args) {
        BusinessHandler handler = new BusinessHandler(new SimpleSellor());
        MyManualDyProxy dyProxy = new MyManualDyProxy(handler);

        dyProxy.advertise();
        dyProxy.advertise();
    }
}
