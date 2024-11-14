package threadDesignPattern.future;

public class Main {
    public static void main(String[] args) {
        Host host = new Host();
        Data data1 = host.request();
        Data data2 = host.request();
        Data data3 = host.request();

        System.out.println("main");
        System.out.println("result1=" + data1.getContent());
        System.out.println("result2=" + data2.getContent());
        System.out.println("result3=" + data3.getContent());
    }
}
