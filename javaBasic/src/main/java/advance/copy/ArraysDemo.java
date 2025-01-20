package advance.copy;

import com.google.gson.Gson;
import concurrent.core2.ClockUtil;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.Arrays;
import java.util.Collection;
import java.util.concurrent.CopyOnWriteArrayList;

@Slf4j
public class ArraysDemo {

    static Gson gson = new Gson();

    public static void main(String[] args) {
       A[] array = new A[2];
        A a1 = new A("xx", 1);
        A a2 = new A("yy", 2);
        array[0] = (a1);
        array[1] = (a2);


        A[] newA = Arrays.copyOf(array, array.length + 1);
        System.out.println(gson.toJson(array));
        System.out.println(gson.toJson(newA));

        //数据元素是浅拷贝，意思是copy前后数据的每个item是引用的复制，新旧item指向
        //的是同一个A对象
        //注意这里的写法，改变的是index=0的item内部/自己的age属性
        //股，原来的source array item也会变化
        //区分newA[0] = new A()这种写法，这种的话相当于新array item指向了新建的A实例
        //这种就与old array没啥关系了
        newA[0].setAge(555);
        System.out.println("....");

        System.out.println(gson.toJson(array));
        System.out.println(gson.toJson(newA));
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    static class A {
        private String name;
        private int age;
    }

    @Test
    public void test(){
        CopyOnWriteArrayList<A> list = new CopyOnWriteArrayList<>();
        A a1 = new A("xx", 1);
        A a2 = new A("yy", 2);
        list.add(a1);
        list.add(a2);

        new Thread(() -> {
            for (A a : list) {
                //开始遍历，不过遍历的很慢
                //遍历期间，B线程删除了index=1的元素
                //那这里A线程还能获取第二个元素吗-照样能读取到
                log.info("item={}", a.toString());
                ClockUtil.sleep(5);
            }
        }).start();

        ClockUtil.sleep(1);

        new Thread(() -> {
            //线程B删除第二个元素，A线程能感知吗，答不能
            //B线程是在list副本上执行的remove的，此刻A线程获取的是原来没有remove的list
            //A，B线程操作的是2个不同的list
            list.remove(1);
            log.info("remove index 1 item end,result");
            show(list);
        }).start();

        System.out.println("end");

        ClockUtil.sleep(10);
    }

    public static void show(Collection collection) {
        log.info(gson.toJson(collection));
    }

}
