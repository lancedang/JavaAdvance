package designPattern.abstractfac;

/**
 * Created by qiankai02 on 2017/8/7.
 */
public class FactoryProducer {
    public static IFactory getFactory(String factoryType) {
        if ("shape".equalsIgnoreCase(factoryType)) {
            return new ShapeFactory();
        } else if ("color".equalsIgnoreCase(factoryType)) {
            return new ColorFactory();
        }
        return null;
    }
}
