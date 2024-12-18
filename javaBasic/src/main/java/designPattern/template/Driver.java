package designPattern.template;

/**
 * Created by qiankai02 on 2017/8/8.
 */
public class Driver {
    public static void main(String[] args) {
        Game callOfDuty = new CallOfDuty();
        Game cs = new CS();
        Game kingHornor = new KingHornor();

        callOfDuty.play();
        cs.play();
        kingHornor.play();

    }
}
