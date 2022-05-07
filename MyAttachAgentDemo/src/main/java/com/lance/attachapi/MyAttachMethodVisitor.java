package com.lance.attachapi;

import jdk.internal.org.objectweb.asm.MethodVisitor;
import jdk.internal.org.objectweb.asm.Opcodes;
import jdk.internal.org.objectweb.asm.Type;
import jdk.internal.org.objectweb.asm.commons.AdviceAdapter;

public class MyAttachMethodVisitor extends AdviceAdapter {

    protected MyAttachMethodVisitor(MethodVisitor mv, int access, String name, String desc) {
        super(Opcodes.ASM4, mv, access, name, desc);
    }


    @Override
    protected void onMethodEnter() {
//        mv.visitIntInsn(BIPUSH, 60); //小于127的值用这个
        mv.visitIntInsn(SIPUSH, 6000);//>127的用这个
        mv.visitInsn(IRETURN);
    }
}
