package concurrent.core2.atom;

import sun.misc.Unsafe;

//unsafe-操作对象-最简demo
public class UnsafeHelloWorld {

    private int color;

    public static void main(String[] args) throws NoSuchFieldException {
        Unsafe unsafe = UnSafeDemo.getUnsafe();

        //1.先获取字段color内存偏移量offset
        long offset = unsafe.objectFieldOffset(UnsafeHelloWorld.class.getDeclaredField("color"));

        UnsafeHelloWorld unsafeHelloWorld = new UnsafeHelloWorld();

        System.out.println("before=" + unsafeHelloWorld.color);

        //2.然后，通过unsafe来给某个对象设置color的值
        unsafe.putInt(unsafeHelloWorld, offset, 100);

        System.out.println("after=" + unsafeHelloWorld.color);


    }
}
