package advance.job.generic.impl;

import advance.job.generic.IArea;

/**
 * Created by Dangdang on 2018/4/10.
 * 定义实现公共接口的具体类
 */
public class Circle implements IArea {

    private Integer radius;

    public Circle(Integer radius) {
        this.radius = radius;
    }

    //实现公共方法
    @Override
    public Double getArea() {
        double area = Math.PI * radius * radius;
        System.out.println(area);
        return area;
    }

    public Integer getRadius() {
        return radius;
    }

    public void setRadius(Integer radius) {
        this.radius = radius;
    }
}
