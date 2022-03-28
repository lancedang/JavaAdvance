package advance.myclass.readresource;

import java.io.IOException;

/**
 * 通过 class 类和 classloader 获取资源的练习
 * 1. class.getResource 包括
 * 1）类所在包路径， param=""，param 不以"/" 开头；
 * 2）项目的 classpath 路径即 /target/classes ，param 以"/" 开头
 *
 * 2. classloader.getResource 只包括 param 不以 "/" 开头，且以 /target/classes 为搜索路径，即 跟上面 2）一样
 *
 */
public class GetResourceDemo {

    public static void main(String[] args) throws IOException {
        int index = 0;
        //class
        Class<GetResourceDemo> demoClass = GetResourceDemo.class;
        System.out.println("demoClass.getName()=\n  " + demoClass.getName());
        //搜寻路径，所在类 GetResourceDemo 的包全路径
        System.out.println("demoClass.getResource(\"\")=\n   " + demoClass.getResource(""));
        //搜寻路径，跟所在类没关系， /target/classes classpath 路径
        System.out.println("demoClass.getResource(\"/\")=\n   " + demoClass.getResource("/"));

        //所在类，包路径下的确存在GetResourceDemo.class
        System.out.println("demoClass.getResource(\"GetResourceDemo.class\")=\n   " + demoClass.getResource("GetResourceDemo.class"));
        //所在类，包路径下不存在 GetResourceDemo01.class 报 null 结果
        System.out.println("demoClass.getResource(\"GetResourceDemo01.class\")=\n   " + demoClass.getResource("GetResourceDemo01.class"));
        //搜寻路径，/target/classes/GetResourceDemo.class 不存在
        System.out.println("demoClass.getResource(\"/GetResourceDemo.class\")=\n   " + demoClass.getResource("/GetResourceDemo.class"));
        //搜寻路径，/target/classes/advance/advance/myclass/readresource/GetResourceDemo.class 的确存在
        System.out.println("demoClass.getResource(\"/advance/myclass/readresource/GetResourceDemo.class\")=\n   " + demoClass.getResource("/advance/myclass/readresource/GetResourceDemo.class"));

        //class.getResource 底层是调用 classloader.getResource()
        System.out.println("..........................................................");
        //搜寻路径，跟所在类没关系， /target/classes classpath 路径
        ClassLoader classLoader = demoClass.getClassLoader();

        System.out.println("classLoader.getResource(\"\")=\n   " + classLoader.getResource(""));
        System.out.println("classLoader.getResource(\"/\")=\n   " + classLoader.getResource("/"));

        //搜寻路径，/target/classes/advance/advance/myclass/readresource/GetResourceDemo.class 的确存在
        System.out.println("classLoader.getResource(\"advance/myclass/readresource/GetResourceDemo.class\")=\n   " + classLoader.getResource("advance/myclass/readresource/GetResourceDemo.class"));
        System.out.println("classLoader.getResource(\"advance/myclass/readresource/GetResourceDemo2.class\")=\n   " + classLoader.getResource("advance/myclass/readresource/GetResourceDemo2.class"));
        System.out.println("classLoader.getResource(\"/advance/myclass/readresource/GetResourceDemo.class\")=\n   " + classLoader.getResource("/advance/myclass/readresource/GetResourceDemo.class"));

        //从 classpath 路径下获取所有资源的 url enumeration
        System.out.println(classLoader.getResources("advance/myclass/readresource/GetResourceDemo.class").nextElement());
    }

}
