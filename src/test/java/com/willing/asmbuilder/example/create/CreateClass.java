package com.willing.asmbuilder.example.create;

import com.willing.asmbuilder.AsmClassBuilder;
import com.willing.asmbuilder.IClass;
import com.willing.asmbuilder.create.AsmEmptyConstructCreate;
import com.willing.asmbuilder.enums.AccessEnum;
import com.willing.asmbuilder.node.KlassNode;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.objectweb.asm.ClassWriter;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(JUnit4.class)
class CreateClass {

    @Test
    void createClass() {
        AsmClassBuilder asmClassBuilder = AsmClassBuilder.classBuilder();

        IClass iClass = new IClass("EmptyTest", "com.willing.test");
        asmClassBuilder
                .buildClass(iClass)
                .buildJava8Version()
                .buildAccess(AccessEnum.APUBLIC)
                .createEmptyConstruct().initAccess(AccessEnum.APUBLIC);
        asmClassBuilder.buildSupperClass(new IClass(Object.class));
       asmClassBuilder.generateClass();
    }
}