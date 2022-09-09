package basic.copy;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Data
public class CloneDemo implements Cloneable {
    private String name;
    private A a;

    public static void main(String[] args) throws CloneNotSupportedException {
        CloneDemo cloneDemo = new CloneDemo();

        A a = new A();
        a.setId("1");

        cloneDemo.name = "xxx";
        cloneDemo.a = a;

        CloneDemo clone = (CloneDemo) cloneDemo.clone();

        log.info("old A.id={},name={}", cloneDemo.a.getId(), cloneDemo.name);
        log.info("new A.id={},name={}", clone.a.getId(), clone.name);

        cloneDemo.a.setId("2");
        cloneDemo.name = "yyy";

        log.info("old A.id={},name={}", cloneDemo.a.getId(), cloneDemo.name);
        log.info("new A.id={},name={}", clone.a.getId(), clone.name);

    }
}

@Data
class A {
    private String id;
}