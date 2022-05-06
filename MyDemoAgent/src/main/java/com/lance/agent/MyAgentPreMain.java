package com.lance.agent;

import java.lang.instrument.Instrumentation;

public class MyAgentPreMain {

    public static void premain(String agentArgs, Instrumentation instrumentation) {

        instrumentation.addTransformer(new MyClassFileTransformer(), true);
    }
}
