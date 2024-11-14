package concurrent.core2.atom;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicReference;

@Data
@Slf4j
public class AtomicReferenceDemo {

    //写成static变量被main中的Runnable匿名函数类使用可以避免final effective问题
    //可以对比在main中直接定义局部变量 User user,然后再new Runnable匿名函数里面使用，
    //会有ide的提示信息

    //且，线程安全一般是对共享的资源进行操作，如何模拟xx是共享的资源呢？
    // 调用方/使用方是static(比如此刻是main),则可以使用static修饰改变量
    // 调用方若是main里new 一个AtomicReferenceDemo实例，则不需要static修饰（通过共同操作这个实力来模拟共享资源）
    static volatile User user = new User("lisi", 0);

    static volatile User user2 = new User("lisi", 0);

    static AtomicReference<User> atomicUserReference = new AtomicReference<>();

    public static void main(String[] args) throws InterruptedException {

        atomicUserReference.set(user2);

        ExecutorService executorService = Executors.newCachedThreadPool();
        ExecutorService safeExecutorService = Executors.newCachedThreadPool();

        CountDownLatch countDownLatch = new CountDownLatch(10);
        CountDownLatch countDownLatch2 = new CountDownLatch(10);

        Runnable unsafeRun = new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 1000; i++) {
                    int count = user.getCount();
                    //创建一个新对象
                    User newUser = new User(user.getName(), count + 1);
                    //直接赋值
                    //出现问题的本质有2
                    //1.是这上下2行代码可能被多个线程切换
                    //2.是引用类型的赋值操作不是原子性的，这个是官方说法（其实本质是组合操作，即需要借用的oldUser来计算新User）
                    //多线程切换，造成oldUser被其他线程(thread2)修改(thread1使用的还是未被修改的oldUser)
                    //解决方式也有2个：
                    //1.是加锁，保证线程唯一和赋值原子性，这个呢1是粒度比较大，2阻碍了多线程
                    //2.是cas,线程执行赋值前，记录expire和target，如不对重试
                    user = newUser;
                }
                countDownLatch.countDown();

            }
        };


        for (int i = 0; i < 10; i++) {
            executorService.submit(unsafeRun);
        }

        countDownLatch.await();

        log.info("user={}", user);

        Runnable safeRun = new Runnable() {
            @Override
            public void run() {

                //每个线程执行个1k次，若每个线程执行1次，一共10个线程也就cas最多10次很少，体现不出来规模效应来
                for (int i = 0; i < 1000; i++) {
                    User user = atomicUserReference.get();

                    User newUser = new User(user.getName(), user.count + 1);

                    while (!atomicUserReference.compareAndSet(user, newUser)) {
                        //cas失败进入spin记录下，可以有个直观感受
                        log.info("cas spin");

                        //sleep可以减少spin次数
                        //ClockUtil.sleep(1);

                        //下面这2行最关键，while cas判断不成功时，说明expire 的user发生变化了
                        //得重新获取expire 对象
                        //同理得设置最新的newUser属性，方式有2
                        //1.重新new 一个对象
                        //2.给while外面的newUser设置最新属性即可
                        user = atomicUserReference.get();
                        //newUser = new User(user.getName(), user.count + 1);
                        newUser.setCount(user.count + 1);
                    }
                    //log.info("ok");
                }
                countDownLatch2.countDown();
            }
        };

        for (int i = 0; i < 10; i++) {
            safeExecutorService.submit(safeRun);
        }

        countDownLatch2.await();

        log.info("safe user={}", atomicUserReference.get());

    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class User {

        private String name;
        private int count;

    }



}
