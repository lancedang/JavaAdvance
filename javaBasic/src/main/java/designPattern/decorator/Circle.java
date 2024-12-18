package designPattern.decorator;

/**
 * Created by Dangdang on 2018/7/21.
 */
public class Circle implements IShape {
    @Override
    public void draw() {
        System.out.println("We are circle");
    }
}
