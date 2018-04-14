package dang.advance.job.generic.GenericEraser;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Dangdang on 2018/4/11.
 */
public class EraserDemo {
    public static void main(String[] args) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {

        List<Integer> list = new ArrayList<>();
        list.add(1);
//        list.add("as");

        Class<? extends List> aClass = list.getClass();
        //找到名字为 add 且参数为 Integer类型的方法，运行报错
        //因为List源码中 add 方法的参数 是 E 类型参数，不存在以 Integer 为参数的方法
        //java.lang.NoSuchMethodException: java.util.ArrayList.add(java.lang.Integer)
        //从中也可看出，唯一标识某个方法的除了方法名外还要加上 方法参数
        //Method addMethod = aClass.getDeclaredMethod("add", Integer.class);

        //而，当你指定add 方法的参数为 Object 类型外，则擦出了上面的泛型参数Integer，即扩大了参数外围，
        //从而造成可以add String 等各种类型的数据
        Method addMethod = aClass.getDeclaredMethod("add", Object.class);

        addMethod.invoke(list, "hello");
        System.out.println(list.get(1));

    }
}
