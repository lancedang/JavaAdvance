package netty.nio;

import concurrent.core2.ClockUtil;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.time.Clock;
import java.util.Iterator;
import java.util.Set;

@Slf4j
public class NonBlockingServer {
    public static void main(String[] args) {
        ServerSocketChannel serverSocketChannel = null;
        Selector selector = null;
        try {
            log.info("---server open---");
            serverSocketChannel = ServerSocketChannel.open();

            //只有在blocking=false时，才可以register selector
            //默认是blocking=true模式
            //若blocking=false后，执行bind()，后面没有while true进程直接退出了-非阻塞嘛，那我就直接退出了
            //故blocking=false后，bind()后必须加while true处理逻辑
            serverSocketChannel.configureBlocking(false);

            selector = Selector.open();
            //必须blocking=FALSE，否则报异常
            //为何只监听OP_ACCEPT操作？可以监听其他的吗
            serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);


            //bind()会让java进程监听这个端口，等待连接，可以查看源代码
            serverSocketChannel.bind(new InetSocketAddress("127.0.0.1", 7));

            //bind()不会阻塞，会继续向下执行，打印如下日志
            log.info("---server bind end---");
            log.info("---server 已启动 ---");

        } catch (IOException e) {
            e.printStackTrace();
        }

        Selector finalSelector = selector;
        new Thread(new Runnable() {
            @Override
            public void run() {
                ClockUtil.sleep(4);
                //唤醒一次，select()阻塞向下走一次
                finalSelector.wakeup();
            }
        }).start();

        while (true) {
            //这个是阻塞操作
            try {
                //阻塞等待监听的channel上面相关事件的发生
                //没发生就一直阻塞着
                int select = selector.select();
            } catch (IOException e) {
                e.printStackTrace();
            }

            log.info("after selector.select，肯定已经发生某个事件了，才能走到这");

            Set<SelectionKey> selectionKeys = selector.selectedKeys();
            Iterator<SelectionKey> iterator = selectionKeys.iterator();

            //遍历发生的所有事件
            while (iterator.hasNext()) {
                SelectionKey key = iterator.next();
                iterator.remove();

                log.info("key acceptable={} connect={}, readable={},writeable={}",
                        key.isAcceptable(),
                        key.isConnectable(),
                        key.isReadable(),
                        key.isWritable());

                try {
                    if (key.isAcceptable()) {
                        log.info("key is Acceptable");
                        //上面不是有server channel了吗，还得通过key再获取一次？
                        ServerSocketChannel server = (ServerSocketChannel) key.channel();
                        log.info("server=serverSocketChannel {}", server == serverSocketChannel);

                        //如果出现可以接收事件时
                        //创建于client一一对应的server端 SocketChannel
                        SocketChannel socketChannel = server.accept();

                        log.info("server receive client 连接 {}", socketChannel);

                        //还需要在server这设置blocking模式？
                        socketChannel.configureBlocking(false);

                        //给server端的SocketChannel注册 selector
                        //相当于selector权利又变大了，可以管到每个与client一一对应的连接了
                        SelectionKey registerKey = socketChannel.register(selector, SelectionKey.OP_READ | SelectionKey.OP_WRITE  );

                        //也不知道为啥要attach这个东西，不attach又怎样？
                        ByteBuffer buffer = ByteBuffer.allocate(100);
                        registerKey.attach(buffer);

                    }

                    if (key.isConnectable()) {

                    }

                    //可读，是指client向channel发送了数据，才会触发，
                    //要不管道里啥也没有，咋触发
                    if (key.isReadable()) {
                        //为啥这里可以强制转成SocketChannel而不是SocketServerChannel
                        SocketChannel client = (SocketChannel) key.channel();

                        log.info("remote client is readable={}", client.getRemoteAddress());

                        ByteBuffer attachment = (ByteBuffer) key.attachment();

                        client.read(attachment);

                        log.info("from client content={}", attachment.toString());
                    }

                    //可以写，是连接建立后，server就一直可以往里面写东西
                    //这个不像readable一样还得等待client发数据过来
                    if (key.isWritable()) {
                        SocketChannel channel = (SocketChannel)key.channel();
                        log.info("remote client is writable={}", channel.getRemoteAddress());
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        }

    }
}
