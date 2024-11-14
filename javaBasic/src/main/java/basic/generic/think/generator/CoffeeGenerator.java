package basic.generic.think.generator;

import basic.generic.think.generator.coffee.*;

import java.util.Iterator;
import java.util.Random;

/**
 * Created by Dangdang on 2017/12/6.
 */
public class CoffeeGenerator implements MyGenerator<Coffee>, Iterable<Coffee> {

    private Class[] coffeeClasses = {Latte.class, Mocha.class, Cappuccino.class, Americano.class, Breve.class};
    private Random random = new Random(47);

    public CoffeeGenerator() {
    }

    //for iteration
    private int size = 0;

    public CoffeeGenerator(int size) {
        this.size = size;
    }

    public Coffee next() {
        //随机获得 class
        Class coffeeClass = coffeeClasses[random.nextInt(coffeeClasses.length)];
        try {
            //返回对象
            Coffee coffee = (Coffee) coffeeClass.newInstance();
            return coffee;
        } catch (InstantiationException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        //return null;
    }


    class CoffeeIterator implements Iterator<Coffee> {
        //定义最大index
        int count = size;

        public boolean hasNext() {
            //count == 0, 没有下一个了
            return count > 0;
        }

        public Coffee next() {
            //获取下一个元素
            count--;
            //???
            return CoffeeGenerator.this.next();
        }

        public void remove() {

        }
    }

    //获得迭代器
    public Iterator<Coffee> getIterator() {
        return new CoffeeIterator();
    }

    public Iterator<Coffee> iterator() {
        return new CoffeeIterator();
    }

    public static void main(String[] args) {
        CoffeeGenerator coffeeGenerator = new CoffeeGenerator();
        for (int i = 0; i < 5; i++) {
            System.out.println(coffeeGenerator.next());
        }
        System.out.println(

        );
        CoffeeGenerator coffeeGenerator1 = new CoffeeGenerator(5);
        for (Coffee c : coffeeGenerator1) {
            System.out.println(c);
        }

    }

}
