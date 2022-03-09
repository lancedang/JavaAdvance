package threadDesignPattern.future;

public class Host {

    public Data request() {
        //1）创建FutureData实例，线程外部创建
        FutureData futureData = new FutureData();
        new Thread(() -> {
            //2）创建真实对象，这个是模拟耗时操作
            RealData realData = new RealData();
            //3）给future设置真实对象
            futureData.setRealData(realData);

        }).start();

        //4)main线程直接返回 伪结果
        return futureData;
    }

}
