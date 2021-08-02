package com.willing.asmbuilder.example.asm;

import com.willing.asmbuilder.util.ByteReaderUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.objectweb.asm.ClassReader;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.tree.ClassNode;
import org.objectweb.asm.tree.InsnList;
import org.objectweb.asm.tree.MethodNode;
import org.objectweb.asm.util.Printer;
import org.objectweb.asm.util.Textifier;
import org.objectweb.asm.util.TraceMethodVisitor;

import java.io.File;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.List;

@RunWith(JUnit4.class)
public class ClassWriterTest {
    @Test
    public void testClassWrite(){
        File file = new File("./controller/C111005Controller.class");
        byte[] bytes = ByteReaderUtil.readerFile(file);
        ClassReader classReader = new ClassReader(bytes);
        ClassNode classNode = new ClassNode();
        classReader.accept(classNode,Opcodes.ASM8);
        viewByteCode(bytes);
    }
    public static void viewByteCode(byte[] bytecode) {
        ClassReader cr = new ClassReader(bytecode);
        ClassNode cn = new ClassNode();
        cr.accept(cn, 0);
        final List<MethodNode> mns = cn.methods;
        Printer printer = new Textifier();
        TraceMethodVisitor mp = new TraceMethodVisitor(printer);
        for (MethodNode mn : mns) {
            InsnList inList = mn.instructions;
            System.out.println(mn.name);
            for (int i = 0; i < inList.size(); i++) {
                inList.get(i).accept(mp);
                StringWriter sw = new StringWriter();
                printer.print(new PrintWriter(sw));
                printer.getText().clear();
                System.out.print(sw.toString());
            }
        }
    }
}
