package netty.aio;

import lombok.extern.slf4j.Slf4j;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousSocketChannel;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.Future;

//模拟流程：
//1.读取terminal数据 -> 2.client写到sever -> 3.client 接收server的返回
//要想保证这个流程：
//需client write()阻塞写入server等待
//需client read()阻塞读取server等待
@Slf4j
public class AsyncClient {
    public static void main(String[] args) {

        String ip = "127.0.0.1";
        int port = 7;

        AsynchronousSocketChannel asynchronousSocketChannel = null;

        try {
            asynchronousSocketChannel = AsynchronousSocketChannel.open();
            Future<Void> connectServerFuture = asynchronousSocketChannel.connect(new InetSocketAddress(ip, port));

            //阻塞等待连接成功，连接server成功之后开始信息交互
            connectServerFuture.get();

            //try() 形式进行资源控制+读取terminal输入
            try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in))) {
                String line;

                //用于与channel进行交互，读写各自一个buffer
                ByteBuffer fromServerBuffer = ByteBuffer.allocate(1024);
                ByteBuffer send2ServerBuffer = ByteBuffer.allocate(1024);

                //
                while ((line = bufferedReader.readLine()) != null) {

                    System.out.println("line=" + line);

                    //准备发送到server的数据
                    send2ServerBuffer.put(line.getBytes(StandardCharsets.UTF_8));

                    send2ServerBuffer.flip();

                    Future<Integer> writeFuture = asynchronousSocketChannel.write(send2ServerBuffer);
                    //因为c-s交互方式就是client先向server发送消息，
                    //所以这里可以阻塞，先完成向server发送数据再执行后面的逻辑
                    //如果不阻塞等，下面的顺序可能错乱
                    writeFuture.get();

                    //从sever读取数据
                    Future<Integer> readFuture = asynchronousSocketChannel.read(fromServerBuffer);
                    //这地方不能阻塞等，也不知为啥
                    //这里是以阻塞形式从server读取消息，最开始肯定一直阻塞，因为client需要首先向server提交数据，
                    //然后server才能返回
                    readFuture.get();

                    fromServerBuffer.flip();

                    byte[] inBytes = new byte[fromServerBuffer.limit()];

                    fromServerBuffer.get(inBytes);
                    //打印上一次的数据
                    System.out.println("from server " + new String(inBytes));

                    fromServerBuffer.clear();
                    send2ServerBuffer.clear();

                }
            } catch (Exception e) {

            };

        } catch (Exception e) {
            e.printStackTrace();

        }






    }

    public void readFromTerminalUpper() {
        //使用高级方式 管理io
        try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in))) {
            String line;
            while ((line = bufferedReader.readLine()) != null) {

            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void readFromTerminal() {
        //从terminal读取用户敲入的内容
        InputStreamReader inputStreamReader = new InputStreamReader(System.in);
        BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

        String line;

        try {
            //while 循环读取terminal数据
            while ((line = bufferedReader.readLine()) != null) {
                System.out.println("user input=" + line);
                if ("exit".equals(line)) {
                    break;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                inputStreamReader.close();
                bufferedReader.close();
            } catch (IOException e) {
                e.printStackTrace();
            } finally {

            }
        }
        System.out.println("exit application");
    }
}
