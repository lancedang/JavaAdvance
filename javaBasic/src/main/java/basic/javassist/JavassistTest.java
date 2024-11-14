package basic.javassist;

import javassist.*;

import java.lang.reflect.Field;
import java.util.Arrays;

public class JavassistTest {
	public static void main2(String[] args) throws Exception {
		ClassPool pool = ClassPool.getDefault();
		CtClass cc = pool.makeClass("basic.javassist.bean.User");

		// 创建属性
		CtField field01 = CtField.make("private int id;", cc);
		CtField field02 = CtField.make("private String name;", cc);
		cc.addField(field01);
		cc.addField(field02);

		// 创建方法
		CtMethod method01 = CtMethod.make("public String getName(){return name;}", cc);
		CtMethod method02 = CtMethod.make("public void setName(String name){this.name = name;}", cc);
		cc.addMethod(method01);
		cc.addMethod(method02);

		// 添加有参构造器
		CtConstructor constructor = new CtConstructor(new CtClass[] { CtClass.intType, pool.get("java.lang.String") },
				cc);
		constructor.setBody("{this.id=id;this.name=name;}");
		cc.addConstructor(constructor);
		// 无参构造器
		CtConstructor cons = new CtConstructor(null, cc);
		cons.setBody("{}");
		cc.addConstructor(cons);

		cc.writeFile("E:/workspace/TestCompiler/src");
	}

	// 获取类的简单信息
	public static void test01() throws Exception {
		ClassPool pool = ClassPool.getDefault();
		CtClass cc = pool.get("basic.javassist.User");

		// 得到字节码
		byte[] bytes = cc.toBytecode();
		System.out.println(Arrays.toString(bytes));

		System.out.println(cc.getName());// 获取类名
		System.out.println(cc.getSimpleName());// 获取简要类名
		System.out.println(cc.getSuperclass());// 获取父类
		System.out.println(cc.getInterfaces());// 获取接口
		System.out.println(cc.getMethods());// 获取
	}

	// 新生成一个方法
	public static void test02() throws Exception {
		ClassPool pool = ClassPool.getDefault();
		CtClass cc = pool.get("basic.javassist.User");

		// 第一种
		// CtMethod cm = CtMethod.make("public String getName(){return name;}",
		// cc);
		// 第二种
		// 参数：返回值类型，方法名，参数，对象
		CtMethod cm = new CtMethod(CtClass.intType, "add", new CtClass[] { CtClass.intType, CtClass.intType }, cc);
		cm.setModifiers(Modifier.PUBLIC);// 访问范围
		cm.setBody("{return $1+$2;}");

		// cc.removeMethod(m) 删除一个方法
		cc.addMethod(cm);
		// 通过反射调用方法
		Class clazz = cc.toClass();
		Object obj = clazz.newInstance();// 通过调用无参构造器，生成新的对象
		java.lang.reflect.Method m = clazz.getDeclaredMethod("add", int.class, int.class);
		Object result = m.invoke(obj, 2, 3);
		System.out.println(result);
	}

	// 修改已有的方法
	public static void test03() throws Exception {
		ClassPool pool = ClassPool.getDefault();
		CtClass cc = pool.get("basic.javassist.User");

		CtMethod cm = cc.getDeclaredMethod("hello", new CtClass[] { pool.get("java.lang.String") });
		cm.insertBefore("System.out.println(\"调用前\");");// 调用前
		cm.insertAt(29, "System.out.println(\"29\");");// 行号
		cm.insertAfter("System.out.println(\"调用后\");");// 调用后

		// 通过反射调用方法
		Class clazz = cc.toClass();
		Object obj = clazz.newInstance();
		java.lang.reflect.Method m = clazz.getDeclaredMethod("hello", String.class);
		Object result = m.invoke(obj, "张三");
		System.out.println(result);
	}

	// 修改已有属性
	public static void test04() throws Exception {
		ClassPool pool = ClassPool.getDefault();
		CtClass cc = pool.get("basic.javassist.User");

		// 属性
		CtField cf = new CtField(CtClass.intType, "age", cc);
		cf.setModifiers(Modifier.PRIVATE);
		cc.addField(cf);
		// 增加响应的get set方法
		cc.addMethod(CtNewMethod.getter("getAge", cf));
		cc.addMethod(CtNewMethod.setter("setAge", cf));

		// 访问属性
		Class clazz = cc.toClass();
		Object obj = clazz.newInstance();
		Field field = clazz.getDeclaredField("age");
		System.out.println(field);
		java.lang.reflect.Method m = clazz.getDeclaredMethod("setAge", int.class);
		m.invoke(obj, 16);
		java.lang.reflect.Method m2 = clazz.getDeclaredMethod("getAge", null);
		Object resutl = m2.invoke(obj, null);
		System.out.println(resutl);
	}

	// 操作构造方法
	public static void test05() throws Exception {
		ClassPool pool = ClassPool.getDefault();
		CtClass cc = pool.get("basic.javassist.User");

		CtConstructor[] cons = cc.getConstructors();
		for (CtConstructor con : cons) {
			System.out.println(con);
		}
	}
	public static void main(String[] args) throws Exception {
		test02();
	}

}
