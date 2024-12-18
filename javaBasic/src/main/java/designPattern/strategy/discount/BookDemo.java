package designPattern.strategy.discount;

/**
 * Created by Dangdang on 2018/4/17.
 */
public class BookDemo {
    public static void main(String[] args) {

        //首先选择一种策略
        SaleContext saleContext = new SaleContext(new JuniorMemberStrategy());

        double price1 = saleContext.purchase(100);

        //再替换成另外一种策略
        saleContext = new SaleContext(new PrimaryMemberStrategy());

        double price2 = saleContext.purchase(100);

        //再替换成另外一种策略
        saleContext = new SaleContext(new SeniorMemberStrategy());

        double price3 = saleContext.purchase(100);

        System.out.println("price1 = " + price1);
        System.out.println("price2 = " + price2);
        System.out.println("price3 = " + price3);

    }
}
