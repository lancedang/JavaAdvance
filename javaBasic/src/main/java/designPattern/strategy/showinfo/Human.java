package designPattern.strategy.showinfo;

/**
 * Created by Dangdang on 2018/4/14.
 */
public class Human implements IStrategy {

    public String showInfo() {
        System.out.println("We are Human");
        return "We are Human";
    }
}
