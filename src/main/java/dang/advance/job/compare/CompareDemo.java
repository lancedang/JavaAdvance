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
        List<Person> persons = new ArrayList<Person>();
        persons.add(new Person("zhangsan", 3));
        persons.add(new Person("lisi", 1));
        persons.add(new Person("wangwu", 4));

        //原顺序
        System.out.println(persons);

        //内部排序
        Collections.sort(persons);

        //排序后顺序
        System.out.println(persons);

        //todo, 创建外排序

    }
}
