package com.willing.asmbuilder;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.StrUtil;
import com.willing.asmbuilder.create.AsmConstructCreate;
import com.willing.asmbuilder.create.AsmEmptyConstructCreate;
import com.willing.asmbuilder.create.AsmFieldCreate;
import com.willing.asmbuilder.create.AsmMethodCreate;
import com.willing.asmbuilder.enums.AccessEnum;
import com.willing.asmbuilder.node.*;
import com.willing.asmbuilder.util.ByteReaderUtil;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.Opcodes;

import java.util.ArrayList;
import java.util.List;

public class AsmClassBuilder {
    private KlassNode clazzNode;

    private String baseDir;

    public static AsmClassBuilder classBuilder() {
        return new AsmClassBuilder();
    }

    public AsmClassBuilder() {
        clazzNode = new KlassNode();
    }

    public AsmClassBuilder buildInterface(IClass[] inter) {
        clazzNode.setInterfaces(inter);
        return this;
    }
    public AsmClassBuilder buildAccess(AccessEnum accessEnum) {
        clazzNode.setAccess(accessEnum.getAccess());
        return this;
    }

    public AsmClassBuilder buildJava8Version() {
        clazzNode.setVersion(Opcodes.V1_8);
        return this;
    }

    public AsmClassBuilder buildWithJsonObject() {
        clazzNode.setIsJson();
        return this;
    }
    public AsmClassBuilder buildWithXmlObject() {
        clazzNode.setIsXml();
        return this;
    }

    public AsmClassBuilder buildSupperClass(IClass superClazz) {
        clazzNode.setSupperClazz(superClazz);
        return this;
    }

    public AsmClassBuilder buildClass(IClass klazz) {
        clazzNode.setKlzz(klazz);
        return this;
    }

    public AsmClassBuilder buildSignature(IClass klazz) {
        clazzNode.setKlzz(klazz);
        return this;
    }


    public AsmEmptyConstructCreate createEmptyConstruct() {
        EmptyInitNode emptyInitNode = new EmptyInitNode();
        clazzNode.setEmptyInitNode(emptyInitNode);
        return new AsmEmptyConstructCreate(emptyInitNode);
    }

    public AsmConstructCreate createConstruct() {

        List<InitNode> constructList = clazzNode.getConstructList();
        if (CollectionUtil.isEmpty(constructList)) {
            constructList = new ArrayList<>();
            clazzNode.setConstructList(constructList);
        }
        InitNode initNode = new InitNode();
        constructList.add(initNode);
        AsmConstructCreate asmConstructBuilder = new AsmConstructCreate(initNode);
        return asmConstructBuilder;
    }

    public AsmMethodCreate createMethod() {
        List<AsmMethodNode> methodNodeList = clazzNode.getMethodNodeList();
        if (CollectionUtil.isEmpty(methodNodeList)) {
            methodNodeList = new ArrayList<>();
            clazzNode.setMethodNodeList(methodNodeList);
        }
        AsmMethodNode initNode = new AsmMethodNode();
        methodNodeList.add(initNode);
        AsmMethodCreate asmConstructBuilder = new AsmMethodCreate(initNode);
        return asmConstructBuilder;
    }

    public AsmFieldCreate createField() {
        List<AsmFieldNode> fieldNodeList = clazzNode.getFieldNodeList();
        if (CollectionUtil.isEmpty(fieldNodeList)) {
            fieldNodeList = new ArrayList<>();
            clazzNode.setFieldNodeList(fieldNodeList);
        }
        AsmFieldNode fieldNode = new AsmFieldNode();
        fieldNodeList.add(fieldNode);
        AsmFieldCreate asmConstructBuilder = new AsmFieldCreate(fieldNode);
        return asmConstructBuilder;
    }

    public String getBaseDir() {
        if (StrUtil.isEmpty(baseDir)){
            return ".\\";
        }
        if (baseDir.endsWith("/")||baseDir.endsWith("\\")) {
            return baseDir;
        }

        return baseDir+"\\";
    }

    public void setBaseDir(String baseDir) {
        this.baseDir = baseDir;
    }

    public void  generateClass(){
        ClassWriter classWriter = new ClassWriter(Opcodes.ASM5);
        clazzNode.validate();
        clazzNode.beforeVisit(classWriter,null);
        clazzNode.afterVisit(classWriter,null);
        byte[] bytes = classWriter.toByteArray();
        String s = clazzNode.generatorClassPath();
        ByteReaderUtil.writeFile(bytes,getBaseDir()+s);
    }

}
