package dang.advance.job.generic;

import java.util.Date;

/**
 * Created by Dangdang on 2018/4/10.
 * 泛型本质上 反面向对象的一种性质（anti-oop）
 * 本质上是abstract(请允许我这里用了一个"抽取"的概念)多种类型间的通用操作,z这是一个自底向上的过程
 * 这种通用操作的概念比接口更广泛，开句玩笑，接口可以是跨越性别谈恋爱，而泛型更广泛到跨越种族
 * 关键点：
 * 1)接口的特性是定义公共行为，即多个实现类实现相同接口方法
 * 2)泛型同样需要所有将要作为类型参数的类具有某个些共同方法，泛型它才能"提纲挈领"式的"抽取"公共方法
 * 3)这种公共方法（泛型中"抽取"的）若在没有使用到泛型之 extentds 关键字的时候，大部分是以 Object 中定义的方法来体现
 * 当，使用了 extends AA 关键字后，则体现在 AA 定义的公共方法上
 * 4)泛型对开发者最有价值的还是体现在 extends 关键字的使用时，此刻可以让用户来定义一个公共边界
 */
public class GenericMethod {

    //需求1：给所有类的 toString 结果前后添加 @ 符号，即 @test@
    public static <T> String show(T t) {
        //t.toString() 方法是所有类型都具有的公共方法, Object 中定义
        //这里我们在泛型方法中借其定义更加公共的通用方法
        String result = "@" + t.toString() + "@";
        System.out.println(result);
        return result;
    }

    //需求2：计算实现IArea 接口的图形的面积, 用 extends 指定类型上限
    public static <T extends IArea> Double getArea(T t) {
        //公共方法来源于 上限类中，此处是IArea 接口定义的方法
        //若不指定 extends getArea() 方法是找不到的
        //用组合方法也可以实现该需求，引入一个IArea 接口
        return t.getArea();
    }

}
