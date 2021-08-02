package com.willing.asmbuilder.create;

import com.willing.asmbuilder.IClass;
import com.willing.asmbuilder.enums.AccessEnum;
import com.willing.asmbuilder.node.AnnotationInfo;
import com.willing.asmbuilder.node.AsmFieldNode;

import java.util.ArrayList;
import java.util.List;

public class AsmFieldCreate {

    private AsmFieldNode fieldNode;

    public AsmFieldCreate(AsmFieldNode fieldNode) {
        this.fieldNode = fieldNode;
    }

    public void initDefaultField(String name){
        fieldNode.setName(name);
        IClass iKlassNode = new IClass(Object.class);
        fieldNode.setType(iKlassNode);
        fieldNode.setAccess(AccessEnum.APUBLIC.getAccess());
    }

    public AsmFieldCreate initSignature(String signature){
        fieldNode.setSignature(signature);
        return this;
    }

    public AsmFieldCreate initAccess(AccessEnum accessEnum){
        fieldNode.setAccess(accessEnum.getAccess());
        return this;
    }

    public AsmFieldCreate initName(String name){
        fieldNode.setName(name);
        return this;
    }

    public AsmFieldCreate initFieldValue(Object initValue){
        fieldNode.setInitValue(initValue);
        return this;
    }

    public AsmFieldCreate initIKlass(IClass clazz){
        fieldNode.setType(clazz);
        return this;
    }
    public AnnotationCreate createAnnotationCreate(){
        List<AnnotationInfo> annotationInfoList = fieldNode.getAnnotationInfoList();
        if (annotationInfoList==null) {
            annotationInfoList = new ArrayList<>();
            fieldNode.setAnnotationInfoList(annotationInfoList);
        }
        AnnotationInfo annotationInfo = new AnnotationInfo();
        AnnotationCreate annotationCreate = new AnnotationCreate(annotationInfo);
        annotationInfoList.add(annotationInfo);
        return annotationCreate;
    }
}
