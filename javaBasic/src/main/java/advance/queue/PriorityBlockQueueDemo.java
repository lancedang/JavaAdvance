package advance.queue;

import lombok.extern.slf4j.Slf4j;

import java.util.PriorityQueue;

@Slf4j
public class PriorityBlockQueueDemo {
    public static void main(String[] args) {

        PriorityQueue<Integer> priorityQueue = new PriorityQueue<>();
        //插入是随机插入的
        priorityQueue.add(2);
        priorityQueue.add(4);
        priorityQueue.add(1);

        while (!priorityQueue.isEmpty()) {
            //便利的时候可以按照item的compareTo方法来排序
            //int类型，输出的是数值大小排序
            //类似，HashMap(遍历：随机)
            // ->LinkedHashMap（遍历：插入顺序）
            // ->TreeMap(遍历：按插入key大小排序)
            log.info("{}", priorityQueue.poll());
        }

    }
}
