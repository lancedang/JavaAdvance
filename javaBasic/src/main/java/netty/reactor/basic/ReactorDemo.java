package netty.reactor.basic;

import java.io.IOException;

public class ReactorDemo {
    public static void main(String[] args) throws IOException {
        new Thread(new Reactor(7)).start();
    }
}
