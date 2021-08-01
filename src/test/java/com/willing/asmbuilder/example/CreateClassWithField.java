package com.willing.asmbuilder.example;

import com.willing.asmbuilder.AsmClassBuilder;
import com.willing.asmbuilder.IClass;
import com.willing.asmbuilder.create.AsmFieldCreate;
import com.willing.asmbuilder.enums.AccessEnum;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.math.BigDecimal;

@RunWith(JUnit4.class)
class CreateClassWithField {

    @Test
    void createClass() {
        AsmClassBuilder asmClassBuilder = AsmClassBuilder.classBuilder();

        IClass iClass = new IClass("OneFieldTest", "com.willing.test");
        asmClassBuilder.buildClass(iClass)
                .buildJava8Version()
                .buildWithJsonObject()
                .buildSupperClass(new IClass(Object.class))
                .buildAccess(AccessEnum.APUBLIC)
                .createEmptyConstruct().initAccess(AccessEnum.APUBLIC);

        AsmFieldCreate field = asmClassBuilder.createField();
        field.initAccess(AccessEnum.APRIVATE);
        IClass iClass1 = new IClass(BigDecimal.class);
        iClass1.setArrays(false);
        field.initIKlass(iClass1);
        field.initName("name");
        field.initFieldValue(new BigDecimal("0.00"));



        asmClassBuilder.generateClass();
    }
}