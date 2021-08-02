package com.willing.asmbuilder.node;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.StrUtil;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.willing.asmbuilder.AbstractNode;
import com.willing.asmbuilder.IClass;
import com.willing.asmbuilder.create.AnnotationCreate;
import org.objectweb.asm.*;

import java.util.ArrayList;
import java.util.List;

public class AsmFieldNode extends AbstractNode {

    private String name;

    private IClass type;


    private Object initValue;

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
        String fullNameType = cn.getKlzz().getFullName();
        //  todo value 是否可以童工这个进行处理。
        FieldVisitor fv = cw.visitField(getAccess(), name, type.generatorArgs(), null, null);
        if (CollectionUtil.isNotEmpty(annotationInfoList)) {
            for (AnnotationInfo asmFieldNode : annotationInfoList) {
                asmFieldNode.visitFieldAnnotation(fv);
            }
        }
        fv.visitEnd();
        MethodVisitor setName = cw.visitMethod(Opcodes.ACC_PUBLIC, "set"+ StrUtil.upperFirst(name), descriptor(null,type), null, null);
        setName.visitCode();
        //this参数
        setName.visitVarInsn(Opcodes.ALOAD, 0);
        //传入的name参数
        setName.visitVarInsn(Opcodes.ALOAD, 1);
        //fullNameType:类的全路径名,即zzz/ddd/ccc/LeakInfo
        setName.visitFieldInsn(Opcodes.PUTFIELD, fullNameType, name, type.generatorArgs());
        setName.visitMaxs(2, 2);
        setName.visitInsn(Opcodes.RETURN);
        setName.visitEnd();

        MethodVisitor getName = cw.visitMethod(Opcodes.ACC_PUBLIC, "get"+ StrUtil.upperFirst(name), descriptor(type,null), null, null);
        getName.visitCode();
        getName.visitVarInsn(Opcodes.ALOAD, 0);
        getName.visitFieldInsn(Opcodes.GETFIELD, fullNameType, name, type.generatorArgs());
        getName.visitMaxs(1, 1);
        getName.visitInsn(Opcodes.ARETURN);
        getName.visitEnd();



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

    public IClass getType() {
        return type;
    }

    public void setType(IClass type) {
        this.type = type;
    }

    public Object getInitValue() {
        return initValue;
    }

    public void setInitValue(Object initValue) {
        this.initValue = initValue;
    }

    public List<AnnotationInfo> getAnnotationInfoList() {
        return annotationInfoList;
    }

    public void setAnnotationInfoList(List<AnnotationInfo> annotationInfoList) {
        this.annotationInfoList = annotationInfoList;
    }

/*    public void visitAnnotation(FieldVisitor field, String name, KlassNode cn){
        if (cn.isJson()) {
            AnnotationVisitor json = field.visitAnnotation("Lcom/fasterxml/jackson/annotation/JsonProperty;", true);
            json.visit("value",name);
            json.visitEnd();
            if (type.getFullName().contains("BigDecimal")) {
                AnnotationVisitor using = field.visitAnnotation("Lcom/fasterxml/jackson/databind/annotation/JsonSerialize;", true);
                Type type = Type.getType(ToStringSerializer.class);
                using.visit("using",type);
                using.visitEnd();
            }
        }else if(cn.isXml()){
            AnnotationVisitor xml = field.visitAnnotation("Lcom/fasterxml/jackson/dataformat/xml/annotation/JacksonXmlProperty;", true);
            xml.visit("localName",name);
            xml.visitEnd();
        }

    }*/
}
