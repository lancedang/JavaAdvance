package advance.job.generic.genericclass;

/**
 * Created by Dangdang on 2018/4/10.
 */
public class Circle {

    private Integer radius;

    public Circle(Integer radius) {
        this.radius = radius;
    }

    @Override
    public String toString() {
        return "radius=" + radius;
    }

    public Integer getRadius() {
        return radius;
    }

    public void setRadius(Integer radius) {
        this.radius = radius;
    }
}
