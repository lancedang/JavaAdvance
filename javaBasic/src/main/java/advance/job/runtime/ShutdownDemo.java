package advance.job.runtime;

public class ShutdownDemo {
    public static void main(String[] args) throws InterruptedException {
        Runtime runtime = Runtime.getRuntime();

        runtime.addShutdownHook(new Thread(() -> {
            System.out.println("jvm run self hook");
        }));

        String command = args[0];
        if (command.equals("exit")) {
            System.out.println("jvm  exit");
            runtime.exit(0);
        } else if (command.equals("halt")) {
            System.out.println("jvm  halt");
            runtime.halt(0);
        } else {
            System.out.println("jvm sleep");
            Thread.sleep(1000*60*60);
        }

    }
}
