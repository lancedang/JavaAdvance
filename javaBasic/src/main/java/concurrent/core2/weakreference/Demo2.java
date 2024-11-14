package concurrent.core2.weakreference;

import lombok.Data;

import java.lang.ref.WeakReference;


@Data
public class Demo2 {

    public static void main2(String[] args) {
        test1();
    }

    private static void test1() {
        Car car = new Car(22000, "silver");
        WeakReference<Car> weakCar = new WeakReference<Car>(car);

        String xx = "xx";
        WeakReference<String> weakXX = new WeakReference<String>(xx);

        Object test = new Object();
        WeakReference<Object> weakY1 = new WeakReference<>(test);

        int i = 0;
        while (true) {
            //ClockUtil.sleep(2);

            if (weakY1.get() != null) {
                //i++;
                System.out.println("weakY1 Object is alive for " + i + " loops - " + weakY1);
            }else {
                System.out.println("weakY1 Object has been collected.");
                break;
            }

            if (weakCar.get() != null) {
                //i++;
                System.out.println("Object is alive for " + i + " loops - " + weakCar);
            } else {
                System.out.println("Object has been collected.");
                break;
            }

            if (weakXX.get() != null) {
                i++;
                System.out.println("xx Object is alive for " + i + " loops - " + weakXX);
            }else {
                System.out.println("xx Object has been collected.");
                break;
            }
        }
    }

    public static void main(String[] args) {
        test1();
    }

    public static void test2() {
        Car car = new Car(22000, "silver");
        WeakReference<Car> weakCar = new WeakReference<Car>(car);

        int i = 0;

        while (true) {
            //这里多打印一行日志，并且使用到target对象
            //target对象指被weakReference修饰的对象
            System.out.println("here is the strong reference 'car' " + car);
            if (weakCar.get() != null) {
                i++;
                System.out.println("Object is alive for " + i + " loops - " + weakCar);
            } else {
                System.out.println("Object has been collected.");
                break;
            }
        }
    }

    static class Car {
        private double price;
        private String colour;

        public Car(double price, String colour) {
            this.price = price;
            this.colour = colour;
        }

        public double getPrice() {
            return price;
        }

        public void setPrice(double price) {
            this.price = price;
        }

        public String getColour() {
            return colour;
        }

        public void setColour(String colour) {
            this.colour = colour;
        }

        public String toString() {
            return colour + "car costs $" + price;
        }

    }
}
