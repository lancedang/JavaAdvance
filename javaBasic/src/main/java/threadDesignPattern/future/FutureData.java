package threadDesignPattern.future;

public class FutureData implements Data {
    private RealData realData;
    private boolean ready = false;

    public synchronized void setRealData(RealData realData) {
        if (ready) { //balking使用if
            return;
        }
        this.realData = realData;
        ready = true;
        notify();
    }

    @Override
    public synchronized String getContent() {
        //guarded suspension使用while，如果结果没有准备好， 则wait
        //也可以使用变种模式：balking,若结果没有准备好，则直接返回
        while (!ready) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        return realData.getContent();
    }
}
