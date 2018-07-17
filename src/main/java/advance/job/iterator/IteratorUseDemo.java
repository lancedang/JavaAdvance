package advance.job.iterator;

import java.util.*;

/**
 * Created by Dangdang on 2018/6/24.
 */
public class IteratorUseDemo {
    public static void main(String[] args) {
        List<String> list = new ArrayList<>(5);

        list.add("1");
        list.add("2");
        list.add("3");

        //获取 ArrayList iterator
        Iterator<String> iterator = list.iterator();

        //List 是有序的，故迭代顺序与插入顺序一致
        while (iterator.hasNext()) {
            String item = iterator.next();
            System.out.println(item);
        }

        //[1, 2, 3]
        System.out.println(list);

        //重新获取 ArrayList iterator，此时返回一个新 iterator，内部每次 new 一个 Iterator 对象并返回
        iterator = list.iterator();
        iterator.next();
        //remove 必须和next() 方法同时是使用，remove 的元素是刚才 next() 方法返回的元素
        iterator.remove();
        //[2, 3]
        System.out.println(list);

        Set<Integer> set = new HashSet<>();
        set.add(4);
        set.add(1);
        set.add(2);

        Iterator<Integer> iterator2 = set.iterator();
        //Iterator 并不能保证遍历顺序与 插入顺序的一致性
        //遍历顺序取决于集合类型是否提供插入顺序的保证
        for (Integer i : set) {
            System.out.println(i);
        }

    }
}
