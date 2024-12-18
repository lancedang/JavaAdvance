package designPattern.spi;

import java.util.Iterator;
import java.util.ServiceLoader;

/**
 * Created by Dangdang on 2018/2/7.
 */
public class SpiDemo {
    public static void main(String[] args) {
        ServiceLoader<Bird> serviceLoader = ServiceLoader.load(Bird.class);

        Iterator<Bird> iterator = serviceLoader.iterator();

        while (iterator.hasNext()) {
            iterator.next().fly();
        }

/*        for (Bird bird : serviceLoader) {
            bird.fly();
        }*/
    }
}
