package com.willing.asmbuilder.util;

import cn.hutool.core.io.BufferUtil;
import cn.hutool.core.io.FileUtil;
import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.tree.ClassNode;
import org.objectweb.asm.tree.InsnList;
import org.objectweb.asm.tree.MethodNode;
import org.objectweb.asm.util.Printer;
import org.objectweb.asm.util.Textifier;
import org.objectweb.asm.util.TraceMethodVisitor;
import org.springframework.util.StreamUtils;

import java.io.*;
import java.nio.CharBuffer;
import java.nio.charset.Charset;
import java.util.List;

public class ByteReaderUtil {

    public static byte[] readerFile(File file) {
        byte[] bytes = new byte[0];
        try {
            FileInputStream fileInputStream = new FileInputStream(file);
            bytes = StreamUtils.copyToByteArray(fileInputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return bytes;
    }

    public static void writeFile(byte[] bytecode,String path){
        FileUtil.writeBytes(bytecode,new File(path));
    }

    public static ClassNode classNode(byte[] bytecode) {

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

       return cn;
    }

}
