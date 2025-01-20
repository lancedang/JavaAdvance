package basic.trycatch;

public class TryCatchFDemo {
    public static void main(String[] args) {
        throwSthInCatch();
    }

    //2层try-catch demo, 验证内层catch throw异常，对外部的影响
    private static void throwSthInCatch() {
        try {
            try {
                System.out.println("内部：try里面");
                throw new RuntimeException("内部：我在try里面抛出的异常");
            } catch (Exception e) {
                System.out.println("内部：catch里面，继续throw个异常");
                throw new RuntimeException("内部抛出：" + e.getMessage() + ";;;我在catch里面抛出的异常");
            } finally {
                System.out.println("内部：finally");
            }

        } catch (Exception e) {
            System.out.println("外层 catch；；" + e.getMessage());
        } finally {
            System.out.println("外层：finally2");
        }
    }
}
