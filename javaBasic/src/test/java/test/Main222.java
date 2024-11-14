package test;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class Main222 {
    public static void main(String[] args) {

        ThreadPoolExecutor executor1 = new ThreadPoolExecutor(1, 1, 1, TimeUnit.MILLISECONDS, new LinkedBlockingQueue<>());

        ThreadPoolExecutor executor = new ThreadPoolExecutor(1, 1, 1, TimeUnit.MILLISECONDS, new LinkedBlockingQueue<>()) {

            @Override
            protected void beforeExecute(Thread t, Runnable r) {
                System.out.println("before");
                super.beforeExecute(t, r);
            }

            @Override
            protected void afterExecute(Runnable r, Throwable t) {
                System.out.println("after");
                super.afterExecute(r, t);
            }
        };

        executor.submit(() -> System.out.println("test"));
    }

    private static final ThreadLocal threadSession = new ThreadLocal();

//    public static Session getSession() throws InfrastructureException {
//        Session s = (Session) threadSession.get();
//        try {
//            if (s == null) {
//                s = getSessionFactory().openSession();
//                threadSession.set(s);
//            }
//        } catch (HibernateException ex) {
//            throw new InfrastructureException(ex);
//        }
//        return s;
//    }


}
