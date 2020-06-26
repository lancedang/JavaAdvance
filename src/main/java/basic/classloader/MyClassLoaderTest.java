// Copyright (C) 2020 Meituan
// All rights reserved
package basic.classloader;

import java.io.*;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Objects;

/**
 * @author qiankai07
 * @version 1.0
 * Created on 6/26/20 9:11 PM
 **/
public class MyClassLoaderTest {
    public static void main(String[] args) throws IOException, IllegalAccessException, InstantiationException, NoSuchMethodException, InvocationTargetException, ClassNotFoundException {
        byte[] bytes = new byte[2048];
        InputStream inputStream = new FileInputStream(new File("/Users/qiankai07/IdeaProjects/JavaAdvance/target/classes/basic/classloader/ClazzLoaderDemo.class"));
        int len = inputStream.read(bytes);

        MyClassLoader myClassLoader = new MyClassLoader();
        Class<?> aClass = myClassLoader.defineMyClass(null, bytes, 0, len);
        Class<?> aClass1 = myClassLoader.loadClass("basic.classloader.ClazzLoaderDemo");
        System.out.println(aClass.getCanonicalName());

        Object o = aClass.newInstance();

        Method method = aClass.getMethod("hi");

        method.invoke(o);

        //输出basic.classloader.MyClassLoader@5acf9800
        System.out.println(aClass.getClassLoader());
        System.out.println(aClass1.getClassLoader());


    }
}