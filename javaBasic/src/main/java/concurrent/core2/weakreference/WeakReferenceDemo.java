package concurrent.core2.weakreference;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.lang.ref.WeakReference;

@Slf4j
public class WeakReferenceDemo {
    public static void main(String[] args) {
        WeakReference<String> weakReference = new WeakReference<>(new String("test"));

        String normalReference = "demo";

        log.info("before gc");

        if (weakReference.get() != null) {
            log.info("weakReference not null before gc");
        }

        System.gc();

        if (weakReference.get() != null) {
            log.info("weakReference not null after gc");
        } else {
            log.info("weakReference  null after gc");
        }

        if (normalReference != null) {
            log.info("normalReference not null after gc");
        } else {
            log.info("normalReference  null after gc");
        }


        //demo2
        String x1 = "xxx";
        String y1 = "yyy";

        InnerBody innerBody = new InnerBody(x1);

        Object test = new Object();
        WeakReference<Object> weakY1 = new WeakReference<>(test);

        x1 = null;
        //y1 = null;

        while (true) {
            log.info("innerBody.getName()=" + innerBody.getName());
            log.info("weakY1.get()=" + weakY1.get());

            //ClockUtil.sleep(3);
            //System.gc();

        }

    }

    @Data
    @AllArgsConstructor
    static class InnerBody {
        private String name;
    }
}
