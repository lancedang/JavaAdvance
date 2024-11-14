package basic;

import java.util.Random;

/**
 * Created by Dangdang on 2017/5/6.
 */
public class RandomDemo {
    public static void main(String[] arrg) {
        Random random = new Random();
        System.out.println(random.nextInt());
        System.out.println(random.nextInt());
        System.out.println(random.nextInt(10));
    }
}
