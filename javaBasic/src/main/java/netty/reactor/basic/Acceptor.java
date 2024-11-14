package netty.reactor.basic;

import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;


@Slf4j
public class Acceptor implements Runnable{

    private final Selector selector;
    private final ServerSocketChannel serverSocketChannel;

    public Acceptor(Selector selector, ServerSocketChannel serverSocketChannel) {
        this.selector = selector;
        this.serverSocketChannel = serverSocketChannel;
    }

    @Override
    public void run() {
        try {
            SocketChannel socketChannel = serverSocketChannel.accept();

            log.info("create new AsyncHandler in Acceptor, accept socketChannel={}", socketChannel);

            new AsyncHandler(selector, socketChannel);

            //把 SocketChannel 又交出去

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
