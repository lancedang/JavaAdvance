package basic.reference;

/**
 * 值传递or引用传递
 */
public class ReferenceOrValue {

    private String name;

    private static final String city = "shanghai";

    public static void main(String[] args) {
        int x = 1;
        change(x);
        System.out.println("old: " + x);

        //city = new String("");

        String name = "zhangsan";
        change(name);
        System.out.println("old: " + name);

        ReferenceOrValue referenceOrValue = new ReferenceOrValue();
        referenceOrValue.name = "hello";

        change(referenceOrValue);
        System.out.println("old: " + referenceOrValue.name);

        ReferenceOrValue referenceOrValue2 = new ReferenceOrValue();
        referenceOrValue2.name = "hello";

        change2(referenceOrValue2);
        System.out.println("old: " + referenceOrValue2.name);


    }

    public static void change(int x) {
        x = 10;
        System.out.println("change: " + x);
    }

    public static void change(String name) {
        name = "lisi";
        System.out.println("change: " + name);
    }

    public static void change(ReferenceOrValue referenceOrValue) {
        referenceOrValue.name = "world";
        System.out.println("change: " + referenceOrValue.name);
    }

    public static void change2(ReferenceOrValue referenceOrValue) {
        referenceOrValue = new ReferenceOrValue();
        referenceOrValue.name = "haha";
        System.out.println("change: " + referenceOrValue.name);
    }

}
