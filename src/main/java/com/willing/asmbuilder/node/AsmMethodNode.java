package com.willing.asmbuilder.node;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.StrUtil;
import com.willing.asmbuilder.AbstractNode;
import com.willing.asmbuilder.IClass;
import org.objectweb.asm.AnnotationVisitor;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

public class AsmMethodNode extends AbstractNode {

    private String name;

    private IClass[] args;

    private IClass returnNode;

    private IClass[] exceptions;

    private MethodVisitor methodVisitor;

    private List<AnnotationInfo> annotationInfoList;

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
         methodVisitor = cw.visitMethod(Opcodes.ACC_PUBLIC, name, descriptor(returnNode,args), null, null);
        methodVisitor.visitCode();


        IClass iClass = new IClass(RequestBody.class);

        AnnotationVisitor annotationVisitor = methodVisitor.visitParameterAnnotation(0, iClass.generatorArgs(), true);
        annotationVisitor.visitEnd();
        if (CollectionUtil.isNotEmpty(annotationInfoList)) {
            for (AnnotationInfo asmFieldNode : annotationInfoList) {
                asmFieldNode.visitMethodAnnotation(methodVisitor);
            }
        }
    }

    @Override
    public void afterVisit(ClassWriter cw, KlassNode cn) {
        if (returnNode==null) {

            methodVisitor.visitInsn(Opcodes.RETURN);
        }else{
            methodVisitor.visitInsn(Opcodes.ARETURN);
        }


        methodVisitor.visitEnd();
    }

    public List<AnnotationInfo> getAnnotationInfoList() {
        return annotationInfoList;
    }

    public void setAnnotationInfoList(List<AnnotationInfo> annotationInfoList) {
        this.annotationInfoList = annotationInfoList;
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
