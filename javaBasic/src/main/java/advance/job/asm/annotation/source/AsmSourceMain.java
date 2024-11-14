package advance.job.asm.annotation.source;


import org.objectweb.asm.ClassReader;
import org.objectweb.asm.tree.AnnotationNode;
import org.objectweb.asm.tree.ClassNode;

import java.io.IOException;

public class AsmSourceMain {
    public static void main(String[] args) throws IOException {
        ClassNode classNode = new ClassNode();

        //ClassReader classReader = new ClassReader("advance.job.asm.annotation.source.MySourceEntity");
        ClassReader classReader = new ClassReader("java.util.ArrayList");
        classReader.accept(classNode, 0);

        System.out.println("class name:" + classNode.name);
        System.out.println("source file:" + classNode.sourceFile);

        System.out.println("invisible:");
        if (classNode.invisibleAnnotations != null) {
            for (Object annotation : classNode.invisibleAnnotations) {
                AnnotationNode annotation1 = (AnnotationNode) annotation;
                System.out.println("annotation descriptor: " + annotation1.desc);
                System.out.println("annotation attribute pair: " + annotation1.values);
            }
        }


        System.out.println("visible:");
        for (Object annotation : classNode.visibleAnnotations) {
            AnnotationNode annotation1 = (AnnotationNode) annotation;
            System.out.println("annotation descriptor: " + annotation1.desc);
            System.out.println("annotation attribute pair: " + annotation1.values);
        }
    }
}
