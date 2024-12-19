package concurrent.core2.weakreference.demo;

import java.lang.ref.WeakReference;

public class MyWeakReference extends WeakReference<Apple> {
    //WeakReference的使用方式也很简单
    //直接继承WeakReference然后传递 真正的普通实例作为参数即可
    public MyWeakReference(Apple referent) {
        super(referent);
    }
}
