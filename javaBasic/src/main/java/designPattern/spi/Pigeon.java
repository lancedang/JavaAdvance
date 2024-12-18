package designPattern.spi;

/**
 * Created by Dangdang on 2018/2/7.
 */
public class Pigeon implements Bird {
    @Override
    public void fly() {
        System.out.println("fly in sky");
    }
}
