package advance.aspect.service;

import advance.aspect.MyAnnotation;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class MyService1 {

    @MyAnnotation(count = 10)
    public void test1() {
        log.info("test1");
    }
}
