package designPattern.strategy.showinfo;

/**
 * Created by Dangdang on 2018/4/14.
 * 定义策略接口，根据经验，我们知道，定义接口是为了运行时调用、解耦具体实现
 */
public interface IStrategy {
    String showInfo();
}
