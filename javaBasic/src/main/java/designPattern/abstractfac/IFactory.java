package designPattern.abstractfac;

/**
 * Created by qiankai02 on 2017/8/7.
 */
public interface IFactory {

    IShape getShape(String shapeType);

    IColor getColor(String colorType);

}