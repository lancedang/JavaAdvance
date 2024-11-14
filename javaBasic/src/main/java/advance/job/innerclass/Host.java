package advance.job.innerclass;

public class Host {
    private final Help help = new Help();

    public void request(String name, int x) {
        new Thread() {
            public void run() {
                //name = "xxx";
                help.show(name + x);
            }
        }.start();
    }

}