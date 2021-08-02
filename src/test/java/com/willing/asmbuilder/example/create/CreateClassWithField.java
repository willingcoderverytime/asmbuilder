package com.willing.asmbuilder.example.create;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.willing.asmbuilder.AsmClassBuilder;
import com.willing.asmbuilder.IClass;
import com.willing.asmbuilder.create.AnnotationCreate;
import com.willing.asmbuilder.create.AsmFieldCreate;
import com.willing.asmbuilder.enums.AccessEnum;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.math.BigDecimal;
import java.util.HashMap;

@RunWith(JUnit4.class)
class CreateClassWithField {

    @Test
    void createClass() {
        AsmClassBuilder asmClassBuilder = AsmClassBuilder.classBuilder();

        IClass iClass = new IClass("OneFieldTest", "com.willing.test");
        asmClassBuilder.buildClass(iClass)
                .buildJava8Version()
                .buildSupperClass(new IClass(Object.class))
                .buildAccess(AccessEnum.APUBLIC)
                .createEmptyConstruct().initAccess(AccessEnum.APUBLIC);
        // step 1 头部注解。


        // step 2 普通属性
        AsmFieldCreate field = asmClassBuilder.createField();
        field.initAccess(AccessEnum.APRIVATE);
        IClass iClass1 = new IClass(BigDecimal.class);
        iClass1.setArrays(false);
        field.initIKlass(iClass1);
        field.initName("name");
        AnnotationCreate annotationCreate = field.createAnnotationCreate();
        annotationCreate.initAnnotationClass(new IClass(JsonProperty.class));
        annotationCreate.addValuesKeyValue("value","name");
        AnnotationCreate toString = field.createAnnotationCreate();
        toString.initAnnotationClass(new IClass(JsonSerialize.class));
        toString.addValuesKeyValue("using", ToStringSerializer.class);

        asmClassBuilder.generateClass();
    }


}