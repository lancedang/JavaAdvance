package designPattern.facade;

import designPattern.facade.items.Fish;
import designPattern.facade.items.Ginger;
import designPattern.facade.items.Pepper;

public class FacadeDemo {

    public static void main(String[] args) {
        //定义材料
        Fish fish = new Fish();
        Ginger ginger = new Ginger();
        Pepper pepper = new Pepper();

        FishFacade fishFacade = new FishFacade(fish, ginger, pepper);

        //调用 facade
        fishFacade.fryFish();

        //如果不使用 facade 则
        //腌鱼
        //fish.pickleFish();

        //油放姜片
        //ginger.fryGinger();

        //油放辣椒
        //pepper.fryPepper();

        //煎鱼
        //fish.fryFish();
    }

}
