// Copyright (C) 2020 Meituan
// All rights reserved
package basic.classloader;

/**
 * @author qiankai07
 * @version 1.0
 * Created on 6/26/20 9:10 PM
 **/
public class MyClassLoader extends ClassLoader {

    public Class<?> defineMyClass(String name, byte[] bytes, int off, int len) {
        return super.defineClass(name, bytes, off, len);
    }

}