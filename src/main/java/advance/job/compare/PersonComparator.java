package advance.job.compare;

import java.util.Comparator;

/**
 * Created by Dangdang on 2018/4/9.
 * 定义外排序，只需实现Comparator 接口
 * 外排序独立于 内排序，不要求 item 实现了Comparable 接口，普通 pojo 即可
 */
public class PersonComparator implements Comparator<Person>{
    @Override
    public int compare(Person o1, Person o2) {

        //定义另外一套排序规则，外排序无需对 item 有 comparable 的要求
        return (o1.getAge() - o2.getAge());

    }
}
