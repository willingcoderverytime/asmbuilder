package com.willing.asmbuilder.node;

import com.willing.asmbuilder.AbstractNode;
import com.willing.asmbuilder.IClass;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;

public class InitNode extends AbstractNode {


    private String name ="<init>";

    private IClass[] args;
    private MethodVisitor constructor;
    @Override
    public void validate() {

    }

    @Override
    protected void nameValidate() {

    }

    @Override
    protected void signatureValidate() {

    }

    @Override
    public void beforeVisit(ClassWriter cw,KlassNode klassNode) {
        String descriptor=descriptor(null,args);

        constructor = cw.visitMethod(Opcodes.ACC_PUBLIC, name, descriptor, null, null);
        constructor.visitVarInsn(Opcodes.ALOAD, 0);
        IClass supperClazz = klassNode.getSupperClazz();
        if (supperClazz!=null) {
            constructor.visitMethodInsn(Opcodes.INVOKESPECIAL, supperClazz.getFullName(), "<init>", "()V",false);
        }else{
            constructor.visitMethodInsn(Opcodes.INVOKESPECIAL, "java/lang/Object", "<init>", "()V",false);
        }
        constructor.visitInsn(Opcodes.RETURN);
        constructor.visitMaxs(1, 1);
    }

    @Override
    public void afterVisit(ClassWriter cw,KlassNode klassNode) {
        constructor.visitEnd();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public IClass[] getArgs() {
        return args;
    }

    public void setArgs(IClass[] args) {
        this.args = args;
    }
}
