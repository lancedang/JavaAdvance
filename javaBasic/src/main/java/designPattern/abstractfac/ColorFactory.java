package designPattern.abstractfac;

/**
 * Created by qiankai02 on 2017/8/7.
 */
public class ColorFactory implements IFactory{
    public IShape getShape(String shapeType) {
        return null;
    }

    public IColor getColor(String colorType) {
        if (colorType == null || colorType.equalsIgnoreCase("")) {
            return null;
        }
        if ("green".equalsIgnoreCase(colorType)) {
            return new Green();
        } else if ("black".equalsIgnoreCase(colorType)) {
            return new Black();
        } else if ("yello".equalsIgnoreCase(colorType)) {
            return new Yellow();
        }
        return null;
    }
}
