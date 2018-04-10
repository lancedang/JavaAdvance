package dang.advance.job.generic.genericclass;

/**
 * Created by Dangdang on 2018/4/10.
 * 定义泛型类，泛型类比泛型方法有更宽泛的概念，泛型方法将类型参数限定在当前方法中
 * 但泛型类，可以将类型参数容纳在field 中
 * 需求：定义一个容器，该容器可以存放任意形状,并且得到该形状的 @toString@ 形式字符串
 */
public class MyContainer<T> {

    //持有对象，任意类型
    private T shape;

    public MyContainer(T shape) {
        this.shape = shape;
    }

    public String getCustomizedString() {
        return "@" + shape.toString() + "@";
    }

    public T getShape() {
        return shape;
    }

    public void setShape(T shape) {
        this.shape = shape;
    }
}
