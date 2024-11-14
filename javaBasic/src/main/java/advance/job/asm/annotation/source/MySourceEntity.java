package advance.job.asm.annotation.source;

public class MySourceEntity {

    @MySourceAnnotation
    public void testSource1() {

    }

    @Deprecated
    public void testSource2() {

    }

    @MyClassAnnotation
    public void clazzMethod() {

    }

    @MyRuntimeAnnotation
    public void runtimeMethod() {

    }

}
