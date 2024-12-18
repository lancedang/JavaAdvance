package designPattern.strategy.showinfo;

/**
 * Created by Dangdang on 2018/4/14.
 */
public class Plant implements IStrategy {

    public String showInfo() {
        System.out.println("We are plant");
        return "We are plant";
    }
}
