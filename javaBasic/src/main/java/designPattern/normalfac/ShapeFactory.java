package designPattern.normalfac;

/**
 * Created by qiankai02 on 2017/8/7.
 */
public class ShapeFactory {
    public IShape getShape(String shapeType) {
        if (shapeType == null || shapeType.equals("")) {
            return null;
        }
        if ("circle".equalsIgnoreCase(shapeType)) {
            return new Circle();
        } else if ("rectangle".equalsIgnoreCase(shapeType)) {
            return new Rectangle();
        } else if ("square".equalsIgnoreCase(shapeType)) {
            return new Square();
        }
        return null;
    }

}
