package designPattern.abstractfac;

/**
 * Created by qiankai02 on 2017/8/7.
 */
public class ItemUser {
    public static void main(String[] args) {
        IFactory shapeFactory = FactoryProducer.getFactory("shape");
        IFactory colorFactory = FactoryProducer.getFactory("color");

        IShape circle = shapeFactory.getShape("circle");
        IColor green = colorFactory.getColor("green");

    }
}
