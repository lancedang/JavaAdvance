package advance.highcpu;

//模拟频繁的young gc
//configuration vm option设置：最大最小均为1g内存
//-Xms1g -Xmx1g -XX:NewRatio=2 -XX:SurvivorRatio=8 -XX:+PrintGCDetails
//while每次创建15m大小数组
public class FrequencyYGCDemo {

    public static void main(String[] args) {
        while (true) {
            // 创建大量对象

            //100万整数 大约15M大小
            //每次gc都会被young gc回收,故不会进入老年代
            Integer[] strings = new Integer[1000000];

            //list.add(strings);

            try {
                // 稍微延迟一下，以便观察GC日志
                Thread.sleep(1);
            } catch (Exception e) {
                System.out.println(e.getMessage());
            } finally {

            }
        }
    }
}

