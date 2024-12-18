package designPattern.strategy.showinfo;

/**
 * Created by Dangdang on 2018/4/14.
 */
public class StrategyDemo {

    public static void main(String[] args) {

        IStrategy animal = new Animal();

        IStrategy human = new Human();

        IStrategy plant = new Plant();

        //通过策略构建器构建不同策略，感觉还饶了一下，直接调接口方法不就得了
        StrategyContext strategyContext = new StrategyContext(animal);
        strategyContext.show();

        //通过这里，我们可以看出，当需要新的策略时，只需自定义一个实现策略接口的 具体策略类
        //然后，使用策略选择器执行该策略
        strategyContext = new StrategyContext(human);
        strategyContext.show();

        strategyContext = new StrategyContext(plant);
        strategyContext.show();
    }

}
