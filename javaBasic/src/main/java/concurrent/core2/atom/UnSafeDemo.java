package concurrent.core2.atom;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import sun.misc.Unsafe;

import java.lang.reflect.Field;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Data
@Slf4j
public class UnSafeDemo {

    public static void main(String[] args) throws InterruptedException {
        HelloLockByUnsafe lock = new HelloLockByUnsafe();

        ExecutorService innerExecutorService = Executors.newCachedThreadPool();

        CountDownLatch countDownLatch = new CountDownLatch(10);


        Runnable unsafeRun = new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 100000; i++) {
                    //操作也很简单
                    lock.selfAddOne();
                }
                countDownLatch.countDown();
            }
        };

        for (int i = 0; i < 10; i++) {
            innerExecutorService.submit(unsafeRun);
        }

        countDownLatch.await();

        log.info("total={}", lock.value);
    }

    private static class HelloLockByUnsafe {
        //这个是用jdk的Unsafe.getUnsafe()实例，会报异常的哦
        //private static final Unsafe unsafe = Unsafe.getUnsafe();
        //这个用反射获取private static unsafe实例
        private static final Unsafe unsafe = getUnsafe();

        //value存储真实数据
        private volatile int value;

        //valueOffset表示字段value在对象中的位置
        //value定义好之后，valueOffset是固定不变的，故用static final表示即可
        private static final long valueOffset;

        static {
            long value1 = 0;
            try {
                //通过unsafe类可以获取类属性的内存地址（相对对象头）
                //可以看出这个offset是固定值一旦确定则不变，static代码块
                value1 = unsafe.objectFieldOffset(HelloLockByUnsafe.class.getDeclaredField("value"));
            } catch (NoSuchFieldException e) {
                e.printStackTrace();
            }
            valueOffset = value1;
        }

        public boolean tryCas(int old, int latest) {
            //用unsafe自带的cas更新
            return unsafe.compareAndSwapInt(this, valueOffset, old, latest);
        }


        //实现无锁自增逻辑,一个工具方法而已
        public void selfAddOne() {
            int old = value;
            //相当于使用Unsafe的compareAndSwapInt()来实现更新
            //借用Unsafe的cas而已
            while (!tryCas(old, old + 1)) {
                old = value;
            }
        }

    }

    public static Unsafe getUnsafe() {
        Unsafe o = null;
        try {
            //使用getDeclaredField获取private变量（不管static与否都能获取）
            //使用getField会报noSuchFieldException
            Field theUnsafe = Unsafe.class.getDeclaredField("theUnsafe");

            theUnsafe.setAccessible(true);

            o = (Unsafe)theUnsafe.get(Unsafe.class);
            //获取static Field时，target是null也ok
            //target分3种：new出来的实例，class实例，null，后2个适用于static变量
            //o = (Unsafe)theUnsafe.get(null);
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (SecurityException e) {
            e.printStackTrace();
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return o;
    }
}
