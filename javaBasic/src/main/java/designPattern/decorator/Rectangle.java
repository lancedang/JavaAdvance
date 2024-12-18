package designPattern.decorator;

/**
 * Created by Dangdang on 2018/7/21.
 */
public class Rectangle implements IShape {
    @Override
    public void draw() {
        System.out.println("We are rectangle");
    }
}
