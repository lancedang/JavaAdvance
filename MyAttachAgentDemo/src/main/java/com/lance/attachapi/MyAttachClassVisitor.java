package com.lance.attachapi;

import jdk.internal.org.objectweb.asm.ClassVisitor;
import jdk.internal.org.objectweb.asm.MethodVisitor;
import jdk.internal.org.objectweb.asm.Opcodes;

public class MyAttachClassVisitor extends ClassVisitor {


    public MyAttachClassVisitor( ClassVisitor cv) {
        super(Opcodes.ASM4, cv);
    }

    @Override
    public MethodVisitor visitMethod(int access, String name, String desc, String signature, String[] exceptions) {
        MethodVisitor methodVisitor = super.visitMethod(access, name, desc, signature, exceptions);

        if (!name.equals("foo")) {
            return methodVisitor;
        }

        return new MyAttachMethodVisitor(methodVisitor, access, name, desc);


    }
}
