package designPattern.normalfac;

/**
 * Created by qiankai02 on 2017/8/7.
 */
public class ShapeUser {
    public static void main(String[] args) {

        ShapeFactory shapeFactory = new ShapeFactory();
        IShape square = shapeFactory.getShape("square");
        IShape rectangle = shapeFactory.getShape("rectangle");
        IShape circle = shapeFactory.getShape("circle");

        /*
        square.showShapeInfo();
        rectangle.showShapeInfo();
        circle.showShapeInfo();
        */

    }
}
