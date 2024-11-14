package concurrent.core2.weakreference.upper;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Data
@AllArgsConstructor
public class A {

    private String name;

    public void set(Object a) {
        Thread thread = Thread.currentThread();
        MyThreadCommon threadCommon = (MyThreadCommon) thread;

        if (threadCommon.localMap == null) {
            threadCommon.localMap = new B();
        }
        threadCommon.localMap.add(this, a);
    }

    public void get() {
        Thread thread = Thread.currentThread();
        MyThreadCommon threadCommon = (MyThreadCommon) thread;

        if (threadCommon.localMap == null) {
            threadCommon.localMap = new B();
        }

        threadCommon.localMap.get(this);
    }

    static class B {
        List<A> aList = new ArrayList<>();

        public void add(A a, Object v) {
            aList.add(a);
        }

        public void get(A a) {
            aList.stream().filter(item -> item == a).findFirst().get();
        }

    }

    @Override
    protected void finalize() throws Throwable {
        super.finalize();
        log.info("finalize A");
    }
}
