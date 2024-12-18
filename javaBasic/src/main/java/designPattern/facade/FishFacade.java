package designPattern.facade;

import designPattern.facade.items.Fish;
import designPattern.facade.items.Ginger;
import designPattern.facade.items.Pepper;

/**
 * 定义红烧鱼的facade
 */
public class FishFacade {

    //把材料准备好：鱼，辣椒，姜片
    private Fish fish;
    private Ginger ginger;
    private Pepper pepper;

    //准备材料
    public FishFacade(Fish fish, Ginger ginger, Pepper pepper) {
        this.fish = fish;
        this.ginger = ginger;
        this.pepper = pepper;
    }

    //红烧鱼有若干步骤的，这里将所有分散步骤统一
    //该方法即所谓的 facade，以煎鱼为整体功能模块，封装了很多繁杂的环节，避免Client 直接各自调用
    public void fryFish() {
        //先把鱼腌一下
        fish.pickleFish();
        //放入辣椒
        pepper.fryPepper();
        //放入姜片
        ginger.fryGinger();
        //煎鱼
        fish.fryFish();
    }

}
