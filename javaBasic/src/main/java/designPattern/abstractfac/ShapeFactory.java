package designPattern.abstractfac;

/**
 * Created by qiankai02 on 2017/8/7.
 */
public class ShapeFactory implements IFactory{
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

    public IColor getColor(String colorType) {
        return null;
    }

}
