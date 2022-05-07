package com.lance.attachapi;

import java.lang.instrument.Instrumentation;
import java.lang.instrument.UnmodifiableClassException;

public class MyAttachAgentMain {
    public static void agentmain(String agentArgs, Instrumentation instrumentation) throws UnmodifiableClassException {
        instrumentation.addTransformer(new MyAttachClassFileTransformer(), true);

        Class[] allLoadedClasses = instrumentation.getAllLoadedClasses();

        for (Class aClass : allLoadedClasses) {
            if (aClass.getName().contains("MyTestMain")) {
                System.out.println("transform class and retransform it=" + aClass.getName());
                instrumentation.retransformClasses(aClass);
            }
        }

    }
}
