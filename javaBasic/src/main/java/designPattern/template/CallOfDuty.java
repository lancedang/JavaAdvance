package designPattern.template;

/**
 * Created by qiankai02 on 2017/8/8.
 */
public class CallOfDuty extends Game {

    void prepare() {
        System.out.println("CallOfDuty is preparing!");
    }

    void fight() {
        System.out.println("CallOfDuty is fighting!");
    }

    void clear() {
        System.out.println("CallOfDuty clear!");
    }
}
