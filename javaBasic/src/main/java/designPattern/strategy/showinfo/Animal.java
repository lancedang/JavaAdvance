package designPattern.strategy.showinfo;

/**
 * Created by Dangdang on 2018/4/14.
 */
public class Animal implements IStrategy {
    //实现策略接口
    public String showInfo() {
        System.out.println("We are animal");
        return "We are animal";
    }
}
