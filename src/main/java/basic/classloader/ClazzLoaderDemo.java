package basic.classloader;

import java.net.URL;

public class ClazzLoaderDemo {
    public static void main(String[] args) {

        for (String s : System.getProperty("sun.boot.class.path").split(";")) {
            System.out.println("bootstrap property =" + s);
        }

        System.out.println("bootstrap property length =" + System.getProperty("sun.boot.class.path").split(";").length);

        for (String s : System.getProperty("java.ext.dirs").split(";")) {
            System.out.println("ext property=" + s);
        }

        System.out.println("java.ext.dirs length =" + System.getProperty("java.ext.dirs").split(";").length);


        for (String s : System.getProperty("java.class.path").split(";")) {
            System.out.println("java.class.path=" + s);
        }

        System.out.println("java.class.path length=" + System.getProperty("java.class.path").split(";").length);


        System.out.println(".....................");
        URL[] urLs = sun.misc.Launcher.getBootstrapClassPath().getURLs();
        for (URL url : urLs) {
            System.out.println("bootstrap classloader url=" + url.toExternalForm());
        }

        System.out.println("bootstrap classloader length=" + urLs.length);

        System.out.println(".....................");

        ClassLoader classLoader = String.class.getClassLoader();
        System.out.println("string class loader=" + classLoader);

        System.out.println("ClassLoader.getSystemClassLoader()=" + ClassLoader.getSystemClassLoader());

        String classPath = System.getProperty("java.class.path");
        for (String path : classPath.split(";")) {
            System.out.println("java.class.path=" + path);
        }

        System.out.println(".....................");

        ClassLoader classLoader2 = ClazzLoaderDemo.class.getClassLoader();
        System.out.println("ClazzLoaderDemo classLoader=" + classLoader2);
        System.out.println("parent = " + classLoader2.getParent());
        System.out.println("parents parent =" + classLoader2.getParent().getParent());

    }

    public void hi(String name) {
        System.out.println(name);
    }
}
