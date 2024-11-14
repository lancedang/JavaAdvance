package advance.job.asm2;

import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.tree.*;

import java.io.*;

public class AsmAPIDemo {
    public static void main(String[] args) throws IOException {
        ClassNode classNode = new ClassNode();

        classNode.version = Opcodes.V1_8;
        classNode.access = Opcodes.ACC_PUBLIC;

        //1)定义类，对应：public class Person
        //classNode.signature = "Ladvance/job/asm2/GenerateClassWithAsm;";
        classNode.signature = "LHelloWorld;";
        //classNode.name = "advance/job/asm2/GenerateClassWithAsm";
        classNode.name = "HelloWorld";
        classNode.superName = "java/lang/Object";

        //2) 定义类中方法，对应 public static void main(String[] args)
        MethodNode methodNode = new MethodNode(
                Opcodes.ACC_PUBLIC | Opcodes.ACC_STATIC,
                "main", "([Ljava/lang/String;)V",
                null, null);

        //3) 定义main方法 System.out.println("Hello World");
        methodNode.instructions.add(new FieldInsnNode(Opcodes.GETSTATIC, "java/lang/System", "out", "Ljava/io/PrintStream;"));
        methodNode.instructions.add(new LdcInsnNode("Hello World!"));
        methodNode.instructions.add(new MethodInsnNode(Opcodes.INVOKEVIRTUAL, "java/io/PrintStream", "println", "(Ljava/lang/String;)V"));


        classNode.methods.add(methodNode);

        ClassWriter classWriter = new ClassWriter(ClassWriter.COMPUTE_MAXS | ClassWriter.COMPUTE_FRAMES);

        classNode.accept(classWriter);

        //4）将字节流保存到class文件中
        File classFileDir = new File("D:\\idea-project\\JavaAdvance\\src\\main\\java\\advance\\job\\asm2");
        DataOutputStream dataOutputStream = new DataOutputStream(new FileOutputStream(new File(classFileDir, "HelloWorld.class")));

        dataOutputStream.write(classWriter.toByteArray());
        dataOutputStream.flush();
        dataOutputStream.close();
    }

    public static void readClassFileByAsm() throws IOException {

        InputStream in = new FileInputStream("out/com/geekyarticles/asm/Generated.class");

        ClassReader cr = new ClassReader(in);
        ClassNode classNode = new ClassNode();

        //ClassNode is a ClassVisitor
        cr.accept(classNode, 0);

        //Let's move through all the methods


    }
}
