package advance.job.javassist;

import javassist.*;

import java.io.IOException;

public class JavassistDemo {
    public static void main(String[] args) throws NotFoundException, CannotCompileException, IOException {
        ClassPool classPool = ClassPool.getDefault();

        //找到class文件
        classPool.insertClassPath("MyJavassistMain.class");

        //获取字节码文件的全限定类名
        CtClass myMainCtClass = classPool.get("advance.job.javassist.MyJavassistMain");

        //定义想要添加的方法,设置方法名，返回类型，方法参数信息
        CtMethod ctMethod = new CtMethod(
                CtClass.voidType,
                "foo",
                new CtClass[]{
                        CtClass.intType,
                        CtClass.intType
                },
                myMainCtClass);

        //设置方法的modifier
        ctMethod.setModifiers(Modifier.PUBLIC);

        //给目的类添加新增的方法
        myMainCtClass.addMethod(ctMethod);

        //重新输出该class文件
        myMainCtClass.writeFile(".");

    }
}
