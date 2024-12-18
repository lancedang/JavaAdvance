package designPattern.strategy.showinfo;

/**
 * Created by Dangdang on 2018/4/14.
 */
public class StrategyContext {

    private IStrategy strategy;

    //是为了加载不同的实现策略
    public StrategyContext(IStrategy strategy) {
        this.strategy = strategy;
    }

    //调用不同策略的实现方法
    public String show() {
        return strategy.showInfo();
    }
}
