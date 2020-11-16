// Copyright (C) 2020 Meituan
// All rights reserved
package advance.reflect.thrift;

import java.util.Arrays;
import java.util.stream.Collectors;

/**
 * @author qiankai07
 * @version 1.0
 * Created on 11/12/20 11:54 AM
 **/
public class ThriftUseReflectDemo {
    public static void main(String[] args) {
        HelloService helloService = new HelloService();

        Class<?>[] classes = HelloService.class.getClasses();

        Arrays.stream(classes).forEach(item -> System.out.println(item.getSimpleName()));

        System.out.println("......");

        Arrays.stream(classes).filter(item -> item.isInterface()).forEach(item -> System.out.println(item.getSimpleName()));

        System.out.println("......");

        Arrays.stream(classes).filter(item -> item.isMemberClass()).forEach(item -> System.out.println(item.getSimpleName()));



    }
}