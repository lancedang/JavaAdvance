package dang.advance.job.generic.genericclass;

import dang.advance.job.generic.IArea;

/**
 * Created by Dangdang on 2018/4/10.
 */
public class Rectangle {

    private Double width;

    private Double height;

    public Rectangle(Double width, Double height) {
        this.width = width;
        this.height = height;
    }

    @Override
    public String toString() {
        return "width=" + width + ", height=" + height;
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
