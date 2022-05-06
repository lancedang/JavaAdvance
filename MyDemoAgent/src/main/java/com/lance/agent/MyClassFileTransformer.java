package com.lance.agent;

import jdk.internal.org.objectweb.asm.ClassReader;
import jdk.internal.org.objectweb.asm.ClassWriter;

import java.lang.instrument.ClassFileTransformer;
import java.lang.instrument.IllegalClassFormatException;
import java.security.ProtectionDomain;

public class MyClassFileTransformer implements ClassFileTransformer {
    @Override
    public byte[] transform(ClassLoader loader, String className, Class<?> classBeingRedefined, ProtectionDomain protectionDomain, byte[] classfileBuffer) throws IllegalClassFormatException {

        //className=sun/launcher/LauncherHelper
        //className=com/lance/agent/MyAgentSourceTest
        System.out.println("className=" + className);
        if (!className.contains("MyAgentSourceTest")) {
            return classfileBuffer;
        }

        //只针对MyAgentSourceTest类进行class transformer处理

        //接收原来的class字节码
        ClassReader classReader = new ClassReader(classfileBuffer);

        ClassWriter classWriter = new ClassWriter(classReader, ClassWriter.COMPUTE_FRAMES);

        //加入自定义的class visit处理器
        MyClassVisitor myClassVisitor = new MyClassVisitor(classWriter);

        classReader.accept(myClassVisitor, ClassReader.SKIP_FRAMES | ClassReader.SKIP_DEBUG);

        //返回加工过的字节码
        return classWriter.toByteArray();

    }
}
