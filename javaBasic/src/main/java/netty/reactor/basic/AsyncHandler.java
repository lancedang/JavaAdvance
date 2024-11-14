package netty.reactor.basic;

import concurrent.core2.ClockUtil;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * AsyncHandler描述的是server端的client请求处理器-就是对请求做响应嘛，也没啥
 * 响应流程包括： <p>
 * 1.读client发过来内容；<p>
 * 2.处理client发过来的内容，比如更新db啥的，发送mq啥的<p>
 * 3.给client返回响应结果
 * <p>
 * 这3个分别对应处理状态标志位：status
 * <p>
 * 1.status: read
 * <p>
 * 2.status: processing，
 * processing处理采用线程池异步处理，仅此而已
 * <p>
 * 3.status: send
 */
@Slf4j
public class AsyncHandler implements Runnable {

    //不管是哪个组件，selector是同一个
    private final Selector selector;
    private final SelectionKey selectionKey;
    private final SocketChannel socketChannel;

    public static final String READ = "readFromClient"; //initial, 因为server肯定是先读client，才能处理、响应client
    public static final String PROCESSING = "processingClient";
    public static final String SEND2CLIENT = "send2Client";

    private final ByteBuffer readByteBuffer = ByteBuffer.allocate(100);
    private final ByteBuffer writeByteBuffer = ByteBuffer.allocate(100);

    //初始状态
    private String status = READ;

    public static final ExecutorService workerExecutor = Executors.newFixedThreadPool(4);

    //这里可以看到若要处理不同的client（也就是SocketChannel）
    //需要构造各自的AsyncHandler对象
    //借此，看清下SocketChannel和AsyncHandler对象的一一对应关系
    //我们需具备这个意识：AsyncHandler是处理所有的SocketChannel呢1：n,还是1:1
    public AsyncHandler(Selector selector, SocketChannel socketChannel) throws IOException {
        //接收传递过来的参数，聚合
        this.selector = selector;
        this.socketChannel = socketChannel;
        this.socketChannel.configureBlocking(false);
        //ops=0表示selector对啥也不感兴趣，啥read,write啥的，全部滚蛋
        //其实也不是全部滚蛋，socket感兴趣的事可以2种方式实现，1.register直接注册；2.通过返回的SelectionKey interestOps实现
        //借此，也可以看出所有的SocketChannel都绑定一个Selector
        //同理，一个SocketChannel对应一个SelectionKey,在这里也就意味着
        //一个AsyncHandler对象对应一个SelectionKey
        selectionKey = socketChannel.register(selector, 0);

        //天网恢恢疏而不漏，socketChannel还是感兴趣一些事，嘿嘿
        selectionKey.interestOps(SelectionKey.OP_READ);
        selectionKey.attach(this);

        //this.selector.wakeup();
    }

    @Override
    public void run() {
        switch (status) {
            case READ:
                log.info("read");
                read();
                break;

            case SEND2CLIENT:
                send();
                break;
        }
    }

    /**
     * 具体执行从client channel读数据任务
     */
    private void read() {
        if (selectionKey.isValid()) {
            readByteBuffer.clear();

            SocketChannel clientChannel = (SocketChannel) selectionKey.channel();

            try {
                //开始读数据
                int readSize = clientChannel.read(readByteBuffer);

                if (readSize > 0) {
                    //读取结束,修改状态，交由Executor异步处理
                    status = PROCESSING;
                    workerExecutor.submit(this::realRead);
                } else {
                    //-1 0
                    log.info("readable size={}", readSize);
                    if (readSize < 0) {
                        clientChannel.close();
                        selectionKey.cancel();
                        log.info("read close");
                    }

                    ClockUtil.sleep(1);

                }

            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }

    private void send() {
        if (selectionKey.isValid()) {
            status = PROCESSING;
            workerExecutor.submit(this::realSend);

            selectionKey.interestOps(SelectionKey.OP_READ);

        }
    }

    private void process() {

    }

    public void realRead() {
        //我咋处理呢，根据readByteBuffer处理
        if (readByteBuffer.hasArray()) {
            readByteBuffer.flip();
            byte[] tempByte = new byte[readByteBuffer.limit()];
            readByteBuffer.get(tempByte);
            log.info("asyncHandler read sth, client--content={}", new String(tempByte));
        }
        //模拟处理耗时
        ClockUtil.sleep(10);

        //处理结束
        status = SEND2CLIENT;

        log.info("interest before ops={}", selectionKey.interestOps());
        selectionKey.interestOps(SelectionKey.OP_WRITE);
        log.info("interest after ops={}", selectionKey.interestOps());

        //因为我处理完了，进入下一个事件了，可以唤醒下

        //其实不唤醒也没啥
        selector.wakeup();

    }


    public void realSend() {
        try {
            writeByteBuffer.clear();
            writeByteBuffer.put("I am server".getBytes(StandardCharsets.UTF_8));

            writeByteBuffer.flip();
            int count = socketChannel.write(writeByteBuffer);

            if (count < 0) {
                selectionKey.cancel();
                socketChannel.close();
                log.info("client close when send msg");
            }

            status = READ;

        } catch (IOException e) {
            e.printStackTrace();
        }
    }




}
