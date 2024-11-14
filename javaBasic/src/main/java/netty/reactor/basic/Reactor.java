package netty.reactor.basic;

import concurrent.core2.ClockUtil;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.SelectableChannel;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.util.Iterator;
import java.util.Set;

@Slf4j
public class Reactor implements Runnable {
    private final Selector selector;

    //定义server端内容
    private final ServerSocketChannel serverSocketChannel;

    public Reactor(int port) throws IOException {
        //创建一个Selector
        this.selector = Selector.open();
        this.serverSocketChannel = ServerSocketChannel.open();

        serverSocketChannel.socket().bind(new InetSocketAddress(port));
        serverSocketChannel.configureBlocking(false);

        SelectionKey selectionKey = serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
        selectionKey.attach(new Acceptor(selector, serverSocketChannel));
    }

    @Override
    public void run() {
        //该线程没有被中断
        try {
            while (!Thread.interrupted()) {
                //一直阻塞等待OP_ACCEPT事件到来，一直阻塞
                //相当于reactor只执行accept连接的事
                //然后将监听到的事件分发出去，其他就不管了
                //spring 的 dispatcherServlet，哈哈
                selector.select();

                Set<SelectionKey> selectionKeys = selector.selectedKeys();

                Iterator<SelectionKey> iterator = selectionKeys.iterator();
                while (iterator.hasNext()) {
                    //
                    SelectionKey key = iterator.next();

                    log.info("channel={},bind event={}", key.channel(), key.interestOps());

                    dispatch(key);
                }
                log.info("完成一次select");
                //这个地方如果不清空的话，外层while true循环继续轮训，又进入iterator一次，造成重复
                selectionKeys.clear();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void dispatch(SelectionKey selectionKey) {
        //上面只有ServerSocketChannel register了Selector，
        //那这里的selectionKey会有除了 ServerSocketChannel  之外的其他的channel吗？？
        SelectableChannel channel = selectionKey.channel();

        ClockUtil.sleep(1);
        log.info("Reactor channel={}", channel);

        Object attachment = selectionKey.attachment();
        Runnable runnable = (Runnable) attachment;
        runnable.run();

    }
}
