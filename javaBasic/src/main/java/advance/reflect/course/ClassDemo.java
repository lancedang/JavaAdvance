package advance.reflect.course;

//该类通过三种方式获取class实例，并通过反射newInitiate获取某个实例
public class ClassDemo {
    public static void main(String[] args) throws ClassNotFoundException, IllegalAccessException, InstantiationException {
        //getClassObject();
        //getInstanceByReflect();

    }

    //通过反射获取实例
    public static void getInstanceByReflect() throws ClassNotFoundException, InstantiationException, IllegalAccessException {
        //取得Class对象的目的是改变实例化对象的方式（区别于new）
        Class<?> bookClass = Class.forName("advance.reflect.course.Book");
        Object book = bookClass.newInstance();
        System.out.println(book);
    }

    //三种方式获取Class对象
    public static void getClassObject() throws ClassNotFoundException {
        //方式1,对象必须存在,这种方式开发过程中不会使用
        String str = "hello";
        Class<? extends String> aClass0 = str.getClass();
        System.out.println(aClass0);

        //方式2,不需要得到实例化对象，少了一个对象的空间，无需实例化对象，类必须存在（严谨的结构）
        Class<String> aClass1 = String.class;
        System.out.println(aClass1);

        //方式3, 不严谨，该类名称可以不存在，只要程序不运行不会出错，提前预留出空间
        Class<?> aClass2 = Class.forName("java.lang.String");
        System.out.println(aClass2);

        //获取接口对象
        Class<?> aClass3 = Class.forName("java.util.List");
        System.out.println(aClass3);

        Class<?> aClass4 = Class.forName("java.lang.Void");
        System.out.println(aClass4);
    }

}


class Book {

    public Book() {
        System.out.println("book constructor");
    }

    @Override
    public String toString() {
        return "this is a book";
    }
}