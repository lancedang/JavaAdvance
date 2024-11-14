package netty.aio;

import javax.persistence.criteria.CriteriaBuilder;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.StandardSocketOptions;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousServerSocketChannel;
import java.nio.channels.AsynchronousSocketChannel;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

public class AsyncServer {
    public static void main(String[] args) {
        int port = 7;
        String host = "127.0.0.1";

        AsynchronousServerSocketChannel asynchronousServerSocketChannel = null;

        try {
            asynchronousServerSocketChannel = AsynchronousServerSocketChannel.open();

            asynchronousServerSocketChannel.setOption(StandardSocketOptions.SO_RCVBUF, 4 * 1024);
            asynchronousServerSocketChannel.setOption(StandardSocketOptions.SO_REUSEADDR, true);

            //这就能异步启动了，牛哇
            asynchronousServerSocketChannel.bind(new InetSocketAddress(host, port));

            System.out.println("server started");

        } catch (IOException e) {
            e.printStackTrace();
        }

        //这得while true监听下，否则上面启动了也白起，直接退出了
        while (true) {

            AsynchronousSocketChannel asynchronousSocketChannel = null;

            //开始监听client
            Future<AsynchronousSocketChannel> future = asynchronousServerSocketChannel.accept();

            try {
                //好，抓到一个
                asynchronousSocketChannel = future.get();

                ByteBuffer fromClientBuffer = ByteBuffer.allocate(1024);
                ByteBuffer sendToClientBuffer = ByteBuffer.allocate(1024);

                System.out.println("listen one client " + asynchronousSocketChannel);

                //从channel里读数据，读出的data放到ByteBuffer里
                //get()是阻塞直到读取结束
                while (asynchronousSocketChannel.read(fromClientBuffer).get() != -1) {

                    System.out.println("in server");

                    //从fromBuffer中读数据
                    //进入读模式
                    fromClientBuffer.flip();

                    //暂存
                    byte[] inByte = new byte[fromClientBuffer.limit()];

                    //写到字节数组
                    fromClientBuffer.get(inByte);

                    System.out.println("read from client content=" + new String(inByte));

                    //清空接收Buffer
                    fromClientBuffer.clear();

                    sendToClientBuffer.put(inByte);
                    sendToClientBuffer.put(" I am server".getBytes(StandardCharsets.UTF_8));

                    sendToClientBuffer.flip();

                    Future<Integer> write2ClientFuture = asynchronousSocketChannel.write(sendToClientBuffer);
                    //等待server发送完数据呀
                    write2ClientFuture.get();

                    //清空发送buffer
                    sendToClientBuffer.clear();

                    System.out.println("server end one round");
                }

            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }


        }

    }
}
