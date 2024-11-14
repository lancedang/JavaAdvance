package basic.basiccache;

public class BaozhuangCache {
    public static void main(String[] args) {

        //这种写法会自动装箱：等价于  Integer.valueOf(11); 这种-128-127 走缓存的
        Integer x1 = 11;
        Integer x2 = 11;

        //都自动装箱，走缓存，故是同一个对象,true
        System.out.println(x1 == x2);

        //这个重新new了一个对象，肯定不相同 false
        Integer x3 = new Integer(11);
        System.out.println(x1 == x3);

        //true
        Integer y1 = Integer.valueOf(11);
        System.out.println(x1 == y1);

        //虽然自动装箱，但是超过了缓存 -128-127 的范围，false
        Integer z1 = 200;
        Integer z2 = 200;

        System.out.println(z1 == z2);

        //自动装箱：等价于 Integer.valueOf()
        Integer m1 = 1;


        //自动拆箱,等价于  m1.intValue()
        int m2 = m1;



    }
}
