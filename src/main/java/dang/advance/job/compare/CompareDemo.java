package dang.advance.job.compare;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Created by Dangdang on 2018/4/9.
 */
public class CompareDemo {
    public static void main(String[] args) {

        //要想构造一个可排序的集合，其中的 item 必须是 Comparable
        List<Person> persons = new ArrayList<Person>();
        persons.add(new Person("zhangsan", 3));
        persons.add(new Person("lisi", 5));
        persons.add(new Person("wangwu", 4));

        //原顺序
        System.out.println(persons);

        //内部排序，当无 sort 方法无 Comparator 参数时，必须要求item 实现 Comparable 接口
        Collections.sort(persons);

        //内排序后顺序
        System.out.println(persons);

        //todo, 创建外排序
        PersonComparator comparator = new PersonComparator();

        //外排序，指定自定义 Comparator，此刻 item 不需要实现Comparable 接口
        Collections.sort(persons, comparator);

        //外排序后顺序
        System.out.println(persons);

    }
}
