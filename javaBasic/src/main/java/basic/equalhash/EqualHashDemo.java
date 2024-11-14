package basic.equalhash;

import java.util.HashMap;
import java.util.Map;

public class EqualHashDemo {

    private String name;

    private int age;

    public EqualHashDemo() {

    }

    public EqualHashDemo(String name, int age) {
        this.age = age;
        this.name = name;
    }

    public static void main(String[] args) {
        Map<EqualHashDemo, String> map = new HashMap<EqualHashDemo, String>();

        EqualHashDemo demo1 = new EqualHashDemo("zhangsan", 2);
        EqualHashDemo demo2 = new EqualHashDemo("zhangsan", 2);

        map.put(demo1, "hello");
        map.put(demo2, "world");

        System.out.println(map.get(demo1));
        System.out.println(map.get(demo2));

        System.out.println(map.size());

    }

    public int hashCode2() {
        //System.out.println("相等");
        return 1;
    }

    public boolean equals(Object object) {
        if (object == null) {
            return false;
        }

        if (this == object) {
            return true;
        }

        if (this.getClass() != object.getClass()) {
            return false;
        }

        //此处已经可以强制类型转化了
        EqualHashDemo object1 = (EqualHashDemo) object;
        return this.name.equals(object1.name) && this.age == object1.age;
    }

}
