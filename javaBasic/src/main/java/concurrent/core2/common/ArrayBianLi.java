package concurrent.core2.common;

import java.util.ArrayList;
import java.util.List;

public class ArrayBianLi {
    public static void main(String[] args) {
        int count0 = 0;
        int[] intArray = new int[2];
        for (int i : intArray) {
            System.out.println("int Array=" + i);
            count0++;
        }
        System.out.println("...,count0=" + count0);

        String[] array = new String[2];
        int count1 = 0;
        for (String s : array) {
            System.out.println("数组：" +s);
            count1 ++;
        }
        System.out.println("...,count1=" + count1);

        int count2 = 0;
        List<String> list = new ArrayList<>(2);
        for (String s : list) {
            System.out.println("list: " + s);
            count2++;
        }
        System.out.println("...,count2=" +count2);
    }
}
