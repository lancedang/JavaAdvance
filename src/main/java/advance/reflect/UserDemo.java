package advance.reflect;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class UserDemo {
    public static void main(String[] args) throws ClassNotFoundException, NoSuchMethodException, SecurityException,
            NoSuchFieldException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        Class<?> c = Class.forName("advance.reflect.User");

        System.out.println(c.getName()); // 全称
        System.out.println(c.getSimpleName()); // 简称
        System.out.println(c.getDeclaredField("name")); // 简称

        Method method1 = c.getMethod("show1", null);
        Method method2 = c.getMethod("show2", new Class[]{String.class});
        Method method3 = c.getMethod("show3", new Class[]{String.class, int.class});
        Method method4 = c.getDeclaredMethod("show4", null);

        Field field1 = c.getField("age");
        Field field2 = c.getDeclaredField("name");
        Field[] fields = c.getFields();

        Constructor<?>[] constructors = c.getConstructors(); //无参构造函数
        Constructor<?> constructor = c.getConstructor(new Class[]{String.class}); //有参构造函数
        Constructor<?> constructor2 = c.getConstructor(String.class, int.class);
        User user = (User) constructor2.newInstance("haoba", 11);

        System.out.println();

        for (Field f : fields) {
            //System.out.println(f.getName());
        }

        //System.out.println(field2.getName()); // 全称

        User u = (User) c.newInstance(); //无参构造函数


        try {
            method1.invoke(u, null);
            method2.invoke(u, "qiankai");
            method3.invoke(u, "sf", 2);
        } catch (IllegalArgumentException | InvocationTargetException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }


        Method[] methods = c.getMethods();

        for (Method m : methods) {
            System.out.println(m.getName());
        }
        System.out.println("...........");
        Method[] declaredMethods =
                c.getDeclaredMethods();
        for (Method m : declaredMethods) {
            System.out.println(m.getName());
        }

    }
}
