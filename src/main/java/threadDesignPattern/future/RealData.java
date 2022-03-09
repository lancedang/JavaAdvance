package threadDesignPattern.future;

import java.util.concurrent.TimeUnit;

public class RealData implements Data{

    public RealData() {
        try {
            //模拟耗时操作
            TimeUnit.SECONDS.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String getContent() {
        return "real data";
    }
}
