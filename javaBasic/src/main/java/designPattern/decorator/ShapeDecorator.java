package designPattern.decorator;

/**
 * Created by Dangdang on 2018/7/21.
 * 装饰器类用于增强被修饰对象的行为
 */
public abstract class ShapeDecorator implements IShape {

    //保存被修饰类
    protected IShape decoratedShape;

    public ShapeDecorator(IShape decoratedShape) {
        this.decoratedShape = decoratedShape;
    }

    //抽象装饰类不对被抽象类进行任何修饰，增强修饰的行为交给子类
    @Override
    public void draw() {
        decoratedShape.draw();
    }

 }
