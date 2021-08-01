package com.willing.asmbuilder.node;

import cn.hutool.core.collection.CollectionUtil;
import com.willing.asmbuilder.AbstractNode;
import com.willing.asmbuilder.IClass;
import org.objectweb.asm.ClassWriter;

import java.util.List;

public class KlassNode extends AbstractNode {

    private Integer version ;

    private IClass klzz;


    private IClass supperClazz;

    private IClass[] interfaces;

    private EmptyInitNode emptyInitNode;
    private List<InitNode> constructList;
    private List<AsmFieldNode> fieldNodeList;
    private List<AsmMethodNode> methodNodeList;


    @Override
    public void validate() {

    }

    @Override
    protected void nameValidate() {

    }

    @Override
    protected void signatureValidate() {

    }

    public String generatorClassPath(){
        String fullName = klzz.getFullName()+".class";
        return fullName;
    }


    @Override
    public void beforeVisit(ClassWriter cw,KlassNode kn) {
        if (supperClazz==null) {
            supperClazz=new IClass(Object.class);
        }
        String[] interfaceArray=null;
        if (interfaces!=null) {
            interfaceArray=new String[interfaces.length];
            for (int i = 0; i < interfaces.length; i++) {
                interfaceArray[i]=interfaces[i].getFullName();
            }
        }
        cw.visit(version,getAccess(),klzz.getFullName(),getSignature(),supperClazz.getFullName(),interfaceArray);
        // 无参数构造开始创建。
        if (emptyInitNode!=null) {
            emptyInitNode.validate();
            emptyInitNode.beforeVisit(cw,this);
        }

        if (CollectionUtil.isNotEmpty(fieldNodeList)) {
            for (AsmFieldNode asmFieldNode : fieldNodeList) {
                asmFieldNode.beforeVisit(cw,this);
            }
        }

        if (CollectionUtil.isNotEmpty(methodNodeList)) {
            for (AsmMethodNode asmFieldNode : methodNodeList) {
                asmFieldNode.beforeVisit(cw,this);
            }
        }

        if (CollectionUtil.isNotEmpty(constructList)) {
            for (InitNode asmFieldNode : constructList) {
                asmFieldNode.beforeVisit(cw,this);
            }
        }
    }

    @Override
    public void afterVisit(ClassWriter cw ,KlassNode kn) {
        if (emptyInitNode!=null) {
            emptyInitNode.validate();
            emptyInitNode.afterVisit(cw,this);
        }
        if (CollectionUtil.isNotEmpty(fieldNodeList)) {
            for (AsmFieldNode asmFieldNode : fieldNodeList) {
                asmFieldNode.afterVisit(cw,this);
            }
        }

        if (CollectionUtil.isNotEmpty(methodNodeList)) {
            for (AsmMethodNode asmFieldNode : methodNodeList) {
                asmFieldNode.afterVisit(cw,this);
            }
        }

        if (CollectionUtil.isNotEmpty(constructList)) {
            for (InitNode asmFieldNode : constructList) {
                asmFieldNode.afterVisit(cw,this);
            }
        }


        cw.visitEnd();
    }


    public EmptyInitNode getEmptyInitNode() {
        return emptyInitNode;
    }

    public void setEmptyInitNode(EmptyInitNode emptyInitNode) {
        this.emptyInitNode = emptyInitNode;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    public IClass getKlzz() {
        return klzz;
    }

    public void setKlzz(IClass klzz) {
        this.klzz = klzz;
    }

    public IClass getSupperClazz() {
        return supperClazz;
    }

    public void setSupperClazz(IClass supperClazz) {
        this.supperClazz = supperClazz;
    }

    public IClass[] getInterfaces() {
        return interfaces;
    }

    public void setInterfaces(IClass[] interfaces) {
        this.interfaces = interfaces;
    }

    public List<InitNode> getConstructList() {
        return constructList;
    }

    public void setConstructList(List<InitNode> constructList) {
        this.constructList = constructList;
    }

    public List<AsmFieldNode> getFieldNodeList() {
        return fieldNodeList;
    }

    public void setFieldNodeList(List<AsmFieldNode> fieldNodeList) {
        this.fieldNodeList = fieldNodeList;
    }

    public List<AsmMethodNode> getMethodNodeList() {
        return methodNodeList;
    }

    public void setMethodNodeList(List<AsmMethodNode> methodNodeList) {
        this.methodNodeList = methodNodeList;
    }
}
