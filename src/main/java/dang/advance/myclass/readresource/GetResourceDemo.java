package dang.advance.myclass.readresource;

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

    public static void main(String[] args) {
        int index = 0;
        //class
        //搜寻路径，所在类 GetResourceDemo 的包全路径
        System.out.println(GetResourceDemo.class.getResource(""));
        //搜寻路径，跟所在类没关系， /target/classes classpath 路径
        System.out.println(GetResourceDemo.class.getResource("/"));

        //所在类，包路径下的确存在GetResourceDemo.class
        System.out.println(GetResourceDemo.class.getResource("GetResourceDemo.class"));
        //所在类，包路径下不存在 GetResourceDemo01.class 报 null 结果
        System.out.println(GetResourceDemo.class.getResource("GetResourceDemo01.class"));
        //搜寻路径，/target/classes/GetResourceDemo.class 不存在
        System.out.println(GetResourceDemo.class.getResource("/GetResourceDemo.class"));
        //搜寻路径，/target/classes/dang/advance/myclass/readresource/GetResourceDemo.class 的确存在
        System.out.println(GetResourceDemo.class.getResource("/dang/advance/myclass/readresource/GetResourceDemo.class"));

        //class.getResource 底层是调用 classloader.getResource()
        System.out.println("..........................................................");
        //搜寻路径，跟所在类没关系， /target/classes classpath 路径
        System.out.println(GetResourceDemo.class.getClassLoader().getResource(""));
        System.out.println(GetResourceDemo.class.getClassLoader().getResource("/"));

        //搜寻路径，/target/classes/dang/advance/myclass/readresource/GetResourceDemo.class 的确存在
        System.out.println(GetResourceDemo.class.getClassLoader().getResource("dang/advance/myclass/readresource/GetResourceDemo.class"));
        System.out.println(GetResourceDemo.class.getClassLoader().getResource("dang/advance/myclass/readresource/GetResourceDemo2.class"));
        System.out.println(GetResourceDemo.class.getClassLoader().getResource("/dang/advance/myclass/readresource/GetResourceDemo.class"));
    }

}
