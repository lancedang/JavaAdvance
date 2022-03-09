package threadDesignPattern.threadlocal;

import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class MyLog {
    private PrintWriter printWriter;

    public MyLog(String name) {

        try {
            printWriter = new PrintWriter(new FileWriter("D://" + name +"-threadLocal.txt"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void print(String i) {
        printWriter.println(i + "");
    }

    public void close() {
        printWriter.close();
    }

}
