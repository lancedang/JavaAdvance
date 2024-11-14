package concurrent.core2.aqs.clh;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

@Slf4j
public class CLHLock implements Lock {

    //使用AtomicReference达到引用类型的原子设置
    private AtomicReference<Node> tail = new AtomicReference<>();

    //保存当前线程对应的锁node
    //每个线程若没有获取到锁，是先放到队列里的，队列元素是node
    //相当于一个node对应一个线程
    private ThreadLocal<Node> threadLocal = new ThreadLocal<>();

    public CLHLock() {
        tail.getAndSet(Node.empty_node);
    }

    public AtomicReference<Node> getTail() {
        return tail;
    }

    @Override
    public void lock() {

        Node currNode = new Node(true, null);

        Node preNode = tail.get();

        //1.先进行cas自旋
        while (!tail.compareAndSet(preNode, currNode)) {
            preNode = tail.get();
        }

        currNode.preNode = preNode;

        //2.再进行普通自旋
        while (preNode.locked) {
            //一直等待上一个节点释放锁,醒来后继续判断locked状态，相当于线程停止了
            //调用lock的线程卡主了**不继续向互斥资源执行**，也就意味着互斥资源不会被多线程同时访问
            try {
                Thread.sleep(0);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        //走到这，说明preNode释放锁了
        //记录当前线程对应的是哪一个node，后面该线程释放锁的时候，得改变对应node的lock状态
        threadLocal.set(currNode);

    }


    @Override
    public void unlock() {
        Node node = threadLocal.get();
        //把已经可以释放的锁对应的curNode的preNode设为null，
        //并不影响这个curNode作为其他节点的preNode
        //pre设置为null，会使最终的node单向队列长度为0
        //不设置的话，进行多少次抢锁，就会有多少个节点，
        //参见测试代码的队列长度输出
        //node.setPreNode(null);
        node.setLocked(false);
        log.info("tail=current?{}", tail.get() == node);
        threadLocal.set(null);
    }

    @Override
    public void lockInterruptibly() throws InterruptedException {

    }

    @Override
    public boolean tryLock() {
        return false;
    }

    @Override
    public boolean tryLock(long time, TimeUnit unit) throws InterruptedException {
        return false;
    }


    @Override
    public Condition newCondition() {
        return null;
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    static class Node {
        //一个线程对应一个node，node locked为true，标志当前线程正霸占着锁
        //每一个抢锁的线程，首先创建一个node，并将node插入队尾
        //获取锁的过程是判断preNode是否是locked状态，只有等待preNode
        private volatile boolean locked;
        private Node preNode;

        public static final Node empty_node = new Node(false, null);

    }
}
