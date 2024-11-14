package concurrent.core2.common;

import com.google.common.collect.Lists;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.collections4.Predicate;

import java.util.ArrayList;
import java.util.Collection;

public class Test {
    public static void main(String[] args) {
        ArrayList<String> strings = Lists.newArrayList("xxx", "yyy", "xyz");

        Collection<String> result = CollectionUtils.select(strings, new Predicate<String>() {
            @Override
            public boolean evaluate(String object) {
                return object.startsWith("x");
            }
        });

    }
}
