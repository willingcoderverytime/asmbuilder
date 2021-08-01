package com.willing.asmbuilder.node;

import com.willing.asmbuilder.AbstractNode;
import com.willing.asmbuilder.IClass;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;

public class AsmMethodNode extends AbstractNode {

    private String name;

    private IClass[] args;

    private IClass returnNode;

    private IClass[] exceptions;
   private MethodVisitor setName;

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
    public void beforeVisit(ClassWriter cw, KlassNode cn) {


    }

    @Override
    public void afterVisit(ClassWriter cw, KlassNode cn) {

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

    public IClass getReturnNode() {
        return returnNode;
    }

    public void setReturnNode(IClass returnNode) {
        this.returnNode = returnNode;
    }

    public IClass[] getExceptions() {
        return exceptions;
    }

    public void setExceptions(IClass[] exceptions) {
        this.exceptions = exceptions;
    }
}
