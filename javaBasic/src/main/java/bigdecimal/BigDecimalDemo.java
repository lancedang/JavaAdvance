package bigdecimal;

public class BigDecimalDemo {
    public static void main(String[] args) {
        float x1 = 1.2f;
        float x2 = 1.1f;
        float x3 = 1.0f;
        float x4 = 0.9f;
        float x5 = 0.8f;

        //这俩个float的差值运算结果相等
        float v1 = x1 - x2;
        float v2 = x2 - x3;

        //擦，这俩个的差值运算结果不相等
        float v3 = x3 - x4;
        float v4 = x4 - x5;

        //基本类型比较用 ==
        System.out.println("v1 == v2," + (v1 == v2));
        System.out.println("v3 == v4," + (v3 == v4));

        Float y1 = x1;
        Float y2 = x2;
        Float y3 = x3;
        Float y4 = x4;
        Float y5 = x5;

        Float w1 = y1 - y2;
        Float w2 = y2 - y3;
        Float w3 = y3 - y4;
        Float w4 = y4 - y5;

        //包装类型比较用equal
        System.out.println("w1.equals(w2)," + w1.equals(w2));
        System.out.println("w3.equals(w4)," +w3.equals(w4));

    }
}
