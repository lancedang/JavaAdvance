package basic.classloader;

import java.net.URL;

public class ClazzLoaderDemo {
    public static void main(String[] args) {
        URL[] urLs = sun.misc.Launcher.getBootstrapClassPath().getURLs();
        for (URL url : urLs) {
            System.out.println(url.toExternalForm());
        }

        ClassLoader classLoader = String.class.getClassLoader();
        System.out.println(classLoader);

        System.out.println(ClassLoader.getSystemClassLoader());

        String classPath = System.getProperty("java.class.path");
        for (String path : classPath.split(";")) {
            System.out.println(path);
        }

        ClassLoader classLoader2 = ClazzLoaderDemo.class.getClassLoader();
        System.out.println(classLoader2);
        System.out.println(classLoader2.getParent());
        System.out.println(classLoader2.getParent().getParent());

    }

    public void hi(String name) {
        System.out.println(name);
    }
}
