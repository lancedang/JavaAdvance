package designPattern.template;

/**
 * Created by qiankai02 on 2017/8/8.
 */
public class KingHornor extends Game {
    void prepare() {
        System.out.println("Kinghornor is preparing!");
    }

    void fight() {
        System.out.println("Kinghornor is fighting!");
    }

    void clear() {
        System.out.println("Kinghornor clear!");
    }
}
