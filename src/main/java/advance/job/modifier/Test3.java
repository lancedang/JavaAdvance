package advance.job.modifier;

/**
 * Created by Dangdang on 2018/6/23.
 */
public class Test3 {
    public static void main(String[] args) {
        //Test1 的 x 是 protected
        Test1 test1 = new Test1();
        //Test3 和 Test1 同 package
        System.out.println(test1.x);

        System.out.println("......................");
        //类 Test3 和 Test1 在同 package 下
        System.out.println(test1.y);

    }
}
