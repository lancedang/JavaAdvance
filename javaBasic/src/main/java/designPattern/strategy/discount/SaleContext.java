package designPattern.strategy.discount;

/**
 * Created by Dangdang on 2018/4/17.
 *
 */
public class SaleContext {

    //持有一个具体的策略对象
    private IBookSaleStrategy bookSaleStrategy;

    //传入一个具体的策略对象
    public SaleContext(IBookSaleStrategy bookSaleStrategy) {
        this.bookSaleStrategy = bookSaleStrategy;
    }

    //计算不同会员（由策略对象指定）购买的 price
    public double purchase(double price) {
        Double bookDiscount = bookSaleStrategy.bookDiscount(price);
        return bookDiscount;
    }

    public IBookSaleStrategy getBookSaleStrategy() {
        return bookSaleStrategy;
    }

    public void setBookSaleStrategy(IBookSaleStrategy bookSaleStrategy) {
        this.bookSaleStrategy = bookSaleStrategy;
    }


}
