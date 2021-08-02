package com.willing.asmbuilder.node;

import cn.hutool.core.collection.CollectionUtil;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.willing.asmbuilder.AbstractNode;
import com.willing.asmbuilder.EnumClass;
import com.willing.asmbuilder.IClass;
import org.objectweb.asm.*;

import java.util.HashMap;
import java.util.Map;

public class AnnotationInfo {

    private IClass annotationClass;

    private HashMap<String, Object> valuesMap;

    public IClass getAnnotationClass() {
        return annotationClass;
    }

    public void setAnnotationClass(IClass annotationClass) {
        this.annotationClass = annotationClass;
    }

    public HashMap<String, Object> getValuesMap() {
        return valuesMap;
    }

    public void setValuesMap(HashMap<String, Object> valuesMap) {
        this.valuesMap = valuesMap;
    }

    public void visitFieldAnnotation(FieldVisitor field) {
        AnnotationVisitor annotationVisitor = field.visitAnnotation(annotationClass.generatorArgs(), true);
        visitAnnotation(annotationVisitor);
        annotationVisitor.visitEnd();
    }

    public void visitMethodAnnotation(MethodVisitor methodVisitor) {
        AnnotationVisitor annotationVisitor = methodVisitor.visitAnnotation(annotationClass.generatorArgs(), true);
        visitAnnotation(annotationVisitor);
        annotationVisitor.visitEnd();
    }

    public void visitClassAnnotation(ClassWriter cw) {
        AnnotationVisitor annotationVisitor = cw.visitAnnotation(annotationClass.generatorArgs(), true);
        visitAnnotation(annotationVisitor);
        annotationVisitor.visitEnd();
    }

    private void visitAnnotation(AnnotationVisitor annotationVisitor) {
        if (CollectionUtil.isNotEmpty(valuesMap)) {
            for (Map.Entry<String, Object> keyValue : valuesMap.entrySet()) {
                String name = keyValue.getKey();
                Object value = keyValue.getValue();
                if (value instanceof EnumClass) {
                    EnumClass value1 = (EnumClass) value;
                    annotationVisitor.visitEnum(name, value1.generatorArgs(),value1.getValue());
                } else if (value instanceof Class) {
                    Type type = Type.getType((Class<?>) value);
                    annotationVisitor.visit(name, type);

                } else if (value instanceof String[]) {
                    String[] value1 = (String[]) value;
                    AnnotationVisitor annotationVisitor1 = annotationVisitor.visitArray(name);
                    for (int i = 0; i < value1.length; i++) {
                        annotationVisitor1.visit(name, value1[i]);
                    }
                    annotationVisitor1.visitEnd();
                } else {
                    annotationVisitor.visit(name, value);
                }
            }
        }
    }

}
