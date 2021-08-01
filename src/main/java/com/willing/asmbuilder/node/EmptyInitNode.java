package com.willing.asmbuilder.node;

import com.willing.asmbuilder.AbstractNode;
import com.willing.asmbuilder.IClass;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;

public class EmptyInitNode extends AbstractNode {

    private String name = "<init>";

    private Integer access;

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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public Integer getAccess() {
        return access;
    }

    @Override
    public void setAccess(Integer access) {
        this.access = access;
    }

    @Override
    public void beforeVisit(ClassWriter cw,KlassNode klassNode) {
        constructor = cw.visitMethod(Opcodes.ACC_PUBLIC, name, "()V", null, null);
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
}
