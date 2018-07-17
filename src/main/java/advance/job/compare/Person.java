package advance.job.compare;

/**
 * Created by Dangdang on 2018/4/9.
 * Comparable 用来表示可比较、可排序的, 顾名思义，可排序指若干个对象的某共同方面特性
 * 比如3本书按照页数多少排序、5个人按照身高排序，在这里有4个方面考虑
 * 1) 可排序的针对性：某对象的一个或多个属性，首先它隶属某个对象，是item的形容词特性，排序的具体规则由具体属性定义
 * 2) 排序的最终目的：对多个对象组成的集合来排序，集合的排序要求其中的item 必须是可排序的，即实现Comparable 接口
 * 3) Comparable 定义的排序规则的自身性(内部定义了一套排序规则)，对集合排序时默认采取item自身的这个排序规则，即实现的compareTo方法
 * 4) 从3)中引申出的是对集合排序还可以定义另外一套排序规则(外排序)，即Comparator的使用
 */
public class Person implements Comparable<Person> { //item 是可排序的

    private String name;

    private int age;

    public Person() {
    }

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    //实现接口方法，定义内部排序规则，可以自定义排序的逻辑，如按照赵钱孙李
    //按照名字字符长度，甚至跟field 无关
    //注意此处的比较对象是this VS 同类的另一个实例，一般集合存放的是相同类型item
    //@Override
    public int compareTo(Person another) {
        //比如此处我定义了 按照名字字符长度来排序
        //todo, 调用 name.compareTo(another.name)
        return this.name.length() - another.name.length();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "name=" + name + ", age=" + age;
    }
}
