package designPattern.decorator;


/**
 * Created by Dangdang on 2018/7/21.
 */
public class RedShapeDecorator extends ShapeDecorator {

    public RedShapeDecorator(IShape decoratedShape) {
        super(decoratedShape);
    }

    //具体decorator对被修饰对象进行了增强
    @Override
    public void draw() {
        decoratedShape.draw();
        //添加具体的修饰行为
        setRedBorder(decoratedShape);
    }

    private void setRedBorder(IShape shape) {
        System.out.println("add red border");
    }

}
