package advance.job.collection;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Dangdang on 2018/6/24.
 * 判断集合中添加 引用类型、值类型再修改元素对集合的影响
 */
public class ListAddItemDemo {
    public static void main(String[] args) {

        List<DItem> items = new ArrayList<>();
        //创建元素 object
        DItem item = new DItem(1);

        items.add(item);
        //[id=1]
        System.out.println(items);

        //修改元素 field
        item.id = 2;
        //[id=2], 集合中保存的还是同一个对象, 只是对象 field 变化了, 但对象未变
        System.out.println(items);

        item = new DItem(3);
        //[id=2]
        System.out.println(items);

        List<String> items2 = new ArrayList<>();
        String s1 = new String("hello");
        items2.add(s1);
        //[hello]
        System.out.println(items2);

        s1 = new String("world");
        //[hello], 元素未变
        System.out.println(items2);

        List<Integer> item3 = new ArrayList<>();
        int x = 1;
        item3.add(x);
        //[1]
        System.out.println(item3);

        x = 2;
        //[1], x 值已经保存到 list 中
        System.out.println(item3);
    }
}

class DItem {
    int id;

    public DItem(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "id=" + id;
    }
}
