package advance.queue;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.DelayQueue;
import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

@Slf4j
public class DelayQueueDemo {
    public static void main(String[] args) throws InterruptedException {

        System.out.println(System.currentTimeMillis());

        try {
            DelayQueue<DelayItem> delayQueue = new DelayQueue<DelayItem>();

            delayQueue.put(new DelayItem(2, "t2"));
            delayQueue.put(new DelayItem(10, "t10"));
            delayQueue.put(new DelayItem(5, "t5"));

            log.info("添加结束");

            //使用一个消费者线程来读取元素
            while (!delayQueue.isEmpty()) {
                log.info("开始读取队列数据");
                DelayItem take = delayQueue.take();
                log.info("现在执行 {} 任务", take.name);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }

    @Data
    static class DelayItem implements Delayed {

        private final String name;
        //定义多长时间后执行该任务,比如5秒后执行该任务（相对时间）
        private final int delayTime;

        //定义该任务执行的绝对时间，比如上午11点0分0秒执（绝对时间）
        private final long realStartTime;

        public DelayItem(int delayTime, String name) {
            this.delayTime = delayTime;
            this.name = name;
            //绝对时间=当前时间+延迟时间
            this.realStartTime = System.currentTimeMillis() + this.delayTime * 1000L;
        }

        @Override
        //表示 任务执行的绝对时间-当前时间=delayPlus；
        //注意：这里的当前时间:是 getDelay()方法被调用的时间，
        //而getDelay()方法会被调用n次（其实是上层在for(;;)死循环中循环调用该方法)
        //而，上层跳出for(;;)的条件是delayPlus <= 0；这个逻辑对理解延迟队列至关重要
        //且注意区分和上面delayTime区别，delayPlus != delayTime
        public long getDelay(TimeUnit unit) {
            long now = System.currentTimeMillis();
            //当前时间到任务到期时间的剩余时间
            long remainDelay = realStartTime - now;
            log.info("----剩余{}毫秒 ", remainDelay);
            return unit.convert(remainDelay, TimeUnit.MILLISECONDS);
        }

        @Override
        public int compareTo(Delayed o) {
            return Long.compare(this.getDelay(TimeUnit.MILLISECONDS), o.getDelay(TimeUnit.MILLISECONDS));
        }
    }
}
