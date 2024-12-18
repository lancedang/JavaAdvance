package designPattern.strategy.discount;

/**
 * Created by Dangdang on 2018/4/17.
 * 定义策略接口
 */
public interface IBookSaleStrategy {
    Double bookDiscount(double price);
}
