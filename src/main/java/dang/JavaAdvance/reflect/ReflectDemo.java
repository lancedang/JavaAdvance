package dang.JavaAdvance.reflect;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

public class ReflectDemo {
	public static void main(String[] args) throws ClassNotFoundException, NoSuchMethodException, SecurityException,
			InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		Boolean v1 = true;

		// 获取类的Class对象
		Class<?> class1 = v1.getClass();
		Class<?> class2 = Class.forName("java.lang.Boolean");
		Class<?> class3 = Boolean.TYPE;
		System.out.println(class1);
		System.out.println(class2);
		System.out.println(class3);

		// 获取类的fields
		Class<?> class4 = MyTest.class;
		System.out.println("class4 = " + class4);

		// 获取公共域
		Field[] fields = class4.getFields();
		System.out.println("............. Field ..............");
		for (Field field : fields) {
			System.out.println("getDeclaredFields = " + field);

			System.out.println("field name = " + field.getName());
			System.out.println("field class = " + field.getType());
			System.out.println("field modifier = " + Modifier.toString(field.getModifiers()));
		}

		// 获取所有域
		fields = class4.getDeclaredFields();
		System.out.println(".............Declared Field ..............");
		for (Field field : fields) {
			System.out.println("getDeclaredFields = " + field);
			System.out.println("field name = " + field.getName());
			System.out.println("field class = " + field.getType());
			System.out.println("field modifier = " + Modifier.toString(field.getModifiers()));
		}

		// 获取Method
		Method[] methods = class4.getMethods();
		System.out.println("............. Method ..............");
		for (Method method : methods) {
			System.out.println("method name = " + method.getName());
			System.out.println("method modifier = " + Modifier.toString(method.getModifiers()));
			System.out.println("method return type = " + method.getReturnType());
			System.out.println("method parameter = " + method.getParameters());
		}
		methods = class4.getDeclaredMethods();
		System.out.println(".............Declared Method ..............");
		for (Method method : methods) {
			System.out.println("method name = " + method.getName());
			System.out.println("method modifier = " + Modifier.toString(method.getModifiers()));
			System.out.println("method return type = " + method.getReturnType());
			System.out.println("method parameter = " + method.getParameters());
		}

		Constructor<?>[] constructors = class4.getConstructors();
		System.out.println(".............Constructor ..............");
		for (Constructor<?> constructor : constructors) {
			System.out.println(constructor);
		}
		Constructor<?> con1 = class4.getConstructor();
		Constructor<?> con2 = class4.getConstructor(int.class);
		Constructor<?> con3 = class4.getConstructor(String.class);
		Constructor<?> con4 = class4.getConstructor(int.class, String.class);
		con1.newInstance();
		con2.newInstance(2);
		con3.newInstance("dangdang");
		con4.newInstance(1, "lili");

	}
}
