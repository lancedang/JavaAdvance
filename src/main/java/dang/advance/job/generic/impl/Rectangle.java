package dang.advance.job.generic.impl;

import dang.advance.job.generic.IArea;

/**
 * Created by Dangdang on 2018/4/10.
 */
public class Rectangle implements IArea {

    private Double width;

    private Double height;

    public Rectangle(Double width, Double height) {
        this.width = width;
        this.height = height;
    }

    @Override
    public Double getArea() {
        double area = width * height;
        System.out.println(area);
        return area;
    }

    public Double getWidth() {
        return width;
    }

    public void setWidth(Double width) {
        this.width = width;
    }

    public Double getHeight() {
        return height;
    }

    public void setHeight(Double height) {
        this.height = height;
    }
}
