package basic.generic.think.generator.coffee;

/**
 * Created by Dangdang on 2017/12/6.
 */
public class Coffee {
    private static long counter = 0;
    private final long id = counter++;

    @Override
    public String toString() {
        return this.getClass().getSimpleName() + " " + id;
    }
}
