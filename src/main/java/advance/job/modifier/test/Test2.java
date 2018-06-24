package advance.job.modifier.test;

import advance.job.modifier.Test1;

/**
 * Created by Dangdang on 2018/6/23.
 */
public class Test2 extends Test1 {

    public static void main(String[] args) {

        //Test1 的 x 是 protected

        Test1 test1 = new Test1();
        //Test2 和 Test1 不同 package，且实例 test1 不是继承的 Test1
        //System.out.println(test1.x);

        Test2 test2 = new Test2();
        //虽 Test2 与 Test1 不同包, 但 Test2 继承 Test1
        System.out.println(test2.x);

        System.out.println("......................................");

        //y 在 Test1 中是 friendly 权限，只能在同 package 下访问
        //System.out.println(test1.y);
        //就算继承也不可以访问必须同 package
        //System.out.println(test2.y);
    }

}
