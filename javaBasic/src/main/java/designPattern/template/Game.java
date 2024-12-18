package designPattern.template;

/**
 * Created by qiankai02 on 2017/8/8.
 * 顶层抽象类，用来定义一系列相继执行或具有某些关联的任务，同时为这些任务的执行定义一个统一的对外接口
 * 而这些具体任务由子类自行实现，但遵循的规则父类已经定好
 */
public abstract class Game {

    abstract void prepare();

    abstract void fight();

    abstract void clear();

    public final void play() {
        prepare();
        fight();
        clear();
    }
}
