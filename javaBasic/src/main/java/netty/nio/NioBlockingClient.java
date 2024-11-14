package netty.nio;

import lombok.extern.slf4j.Slf4j;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.nio.charset.StandardCharsets;

@Slf4j
public class NioBlockingClient {
    public static void main(String[] args) {
        String hostName  = "127.0.0.1";;
        int portNumber = 7;

        SocketChannel socketChannel = null;
        try {

            socketChannel = SocketChannel.open();

            log.info("blocking mode ={}", socketChannel.isBlocking());
            //socketChannel.configureBlocking(false);
            log.info("blocking mode ={}", socketChannel.isBlocking());

            socketChannel.connect(new InetSocketAddress(hostName, portNumber));


        } catch (IOException e) {
            System.err.println("NonBlockingEchoClient异常： " + e.getMessage());
            System.exit(1);
        }


        try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in))) {

            String line = null;

            ByteBuffer write2SeverBuffer = ByteBuffer.allocate(100);

            while ((line = bufferedReader.readLine()) != null) {

                log.info("user input {}", line);
                write2SeverBuffer.put(line.getBytes(StandardCharsets.UTF_8));
                socketChannel.write(write2SeverBuffer);

                write2SeverBuffer.clear();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
