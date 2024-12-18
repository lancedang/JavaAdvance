package designPattern.spi;

/**
 * Created by Dangdang on 2018/2/7.
 */
public class Chicken implements Bird {
    @Override
    public void fly() {
        System.out.println("fly on ground");
    }
}
