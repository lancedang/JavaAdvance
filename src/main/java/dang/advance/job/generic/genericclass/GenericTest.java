package dang.advance.job.generic.genericclass;

import java.util.Date;

/**
 * Created by Dangdang on 2018/4/11.
 */
public class GenericTest {
    public static void main(String[] args) {

        Circle circle = new Circle(2);

        Rectangle rectangle = new Rectangle(new Double(2), new Double(3));

        MyContainer container1 = new MyContainer(circle);
        MyContainer container2 = new MyContainer(rectangle);

        String str1 = container1.getCustomizedString();
        String str2 = container2.getCustomizedString();

        System.out.println(str1);
        System.out.println(str2);

    }
}
