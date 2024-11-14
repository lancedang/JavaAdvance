package advance.job.innerclass;

public class AnnyClassLocalField {
    public static void main(String[] args) {

        Host host = new Host();
        host.request("lisi", 1);
        host.request("shangsan ", 2);

        System.out.println("hi main");
    }


}