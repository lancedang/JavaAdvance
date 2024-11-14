package dang.advance.job.collection.concurrent;

import java.util.HashMap;
import java.util.Map;

public class ConcurrentMapDemo {

    private Map<String, String> map = new HashMap();

    public void addItem(String key, String value) {
        map.put(key, value);
    }

    public static void main(String[] args) {

        final ConcurrentMapDemo demo = new ConcurrentMapDemo();
    }


}
