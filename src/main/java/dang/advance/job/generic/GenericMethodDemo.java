package dang.advance.job.generic;

import dang.advance.job.generic.impl.Circle;
import dang.advance.job.generic.impl.Rectangle;

import java.util.Date;

/**
 * Created by Dangdang on 2018/4/10.
 */
public class GenericMethodDemo {

    public static void main(String[] args) {

        //泛型方法测试，无 extends
        String a = "lisi";
        Integer b = 4;
        Date date = new Date();

        GenericMethod.show(a);

        GenericMethod.show(b);

        GenericMethod.show(date);

        System.out.println("...............");

        //泛型方法测试，无 extends
        Circle circle = new Circle(3);

        GenericMethod.getArea(circle);

        Rectangle rectangle = new Rectangle(new Double(2.0), new Double(3.0));

        GenericMethod.getArea(rectangle);


    }
}
