package concurrent.core2.weakreference.demo;

import java.lang.ref.WeakReference;

public class MyWeakReference extends WeakReference<Apple> {
    public MyWeakReference(Apple referent) {
        super(referent);
    }
}
